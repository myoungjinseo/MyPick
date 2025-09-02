package com.develop.mypick.api.Recommended.service;

import com.develop.mypick.api.Recommended.dto.response.ChatGptResponse;
import com.develop.mypick.api.Recommended.dto.response.RecommendedResponse;
import com.develop.mypick.common.exception.ErrorCode;
import com.develop.mypick.common.exception.ErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.develop.mypick.api.Recommended.dto.request.ChatGptRequest;
import com.develop.mypick.api.Recommended.dto.request.MessageRequest;
import com.develop.mypick.api.userPhysical.service.UserPhysicalService;
import com.develop.mypick.common.config.ChatGptConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.develop.mypick.domain.Recommended.entity.Recommended;
import com.develop.mypick.domain.Recommended.repo.RecommendedRepository;
import com.develop.mypick.domain.user.entity.AuthUser;
import com.develop.mypick.domain.userPhysical.entity.UserPhysical;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ChatGptService {
    private final RecommendedRepository recommendedRepository;
    private final ChatGptConfig gptConfig;
    private final UserPhysicalService userPhysicalService;

    @Value("${chatgpt.url}")
    private String url;

    @Value("${chatgpt.model}")
    private String model;

    @Transactional
    public ChatGptResponse sendChatGpt(AuthUser user) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String prompt = createPrompt(user);
        List<MessageRequest> messages = creatMessages(prompt);
        ChatGptRequest request = ChatGptRequest.of(model,messages,0.2f);
        JsonNode result = getJsonNode(request);
        String json = mapper.writeValueAsString(result);
        String replaceJson = replaceJson(json);
        RecommendedResponse readValue = mapper.readValue(replaceJson, new TypeReference<RecommendedResponse>(){});
        Recommended recommended = RecommendedResponse.toEntity(user,readValue);
        Boolean existsByAuthUser = recommendedRepository.existsByAuthUser(user);
        if (!existsByAuthUser){
            Recommended save = recommendedRepository.save(recommended);
            return ChatGptResponse.of(user,save,readValue);
        }
        Recommended updateRecommended = updateReCommended(user, recommended);

        return ChatGptResponse.of(user,updateRecommended,readValue);
    }


    public JsonNode getJsonNode(ChatGptRequest request) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = gptConfig.httpHeaders();
        HttpEntity requestEntity = new HttpEntity(request,headers);
        String response = gptConfig.restTemplate().postForObject(url,requestEntity,String.class);
        JsonNode root = mapper.readTree(response);
        JsonNode result = root.at("/choices/0/message/content");
        if (result.isNull()){
            throw new ErrorException(ErrorCode.CHATGPT_ERROR);
        }
        return result;
    }

    public String createPrompt(AuthUser user){
        UserPhysical userPhysical = userPhysicalService.findUserPhysical(user);
        String prompt = String.format("형식\\n\\\"기초대사량 : double 칼로리 : double \\n 단백질 : double \\n 탄수화물 : double \\n 지방 : double " +
                "\\n 나트륨 : double \\n칼륨 : double \\n 섬유질 : double \\n 당류 : double \\n 칼슘 : double\\n비타민C : double" +
                "\\n비타민B12 : double\\n비타민E : double\\\"\\n예시 = [- 목적 : %s\\n- 키 : %f cm\\n- 몸무게 : %f kg\\n- 나이 : %d세" +
                "\\n- 활동지수 : %f\\n- 지병 : %s\\n- 성별 : %s]\n고려 사항[1.형식의 형태를 유지해주세요." +
                "\\n2.예시안에 있는 내용을 모두 꼭 참고해주세요.\\n3.단위는 작성을 하지마세요.\\n4.소수점 한자리 수까지 작성해주세요.\\n5.열정적으로 답변해주세요.\\n6.json문장 구조로 표현해주세요.]"
                ,userPhysical.getGoal().getGoal(),userPhysical.getHeight(),userPhysical.getWeight(),userPhysical.getAge(), userPhysical.getActivityLevel().getValue(),
                (!userPhysical.getChronicDiseases().isEmpty() ?userPhysical.getChronicDiseases() : "없음") ,userPhysical.getGender().getGender());
        log.info(prompt);
        return prompt;
    }

    public List<MessageRequest> creatMessages(String prompt){
        MessageRequest systemRequest = MessageRequest.toDto("system","you are a hospital doctor.");
        MessageRequest userRequest = MessageRequest.toDto("user",prompt);
        List<MessageRequest> messages = new ArrayList<>() {{
            add(systemRequest);
            add(userRequest);
        }};
        return messages;
    }

    public String replaceJson(String json){
        return json.substring(1,json.length()-1)
                .replaceAll("\n"," ")
                .replaceAll("n"," ")
                .replaceAll("\\\\"," ");
    }

    @Transactional
    public Recommended updateReCommended(AuthUser user,Recommended updateRecommended){
        Recommended recommended = recommendedRepository.findByAuthUser(user)
                .orElseThrow(()-> new ErrorException(ErrorCode.NOT_FOUND_RECOMMENDED));
        recommended.updateRecommended(updateRecommended);

        return recommended;
    }
}
