# java 17
FROM openjdk:17-jdk

# 인자 정리 - jar
ARG JAR_FILE=/build/libs/*.jar

# jar File Copy
COPY ${JAR_FILE} /app.jar


ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker","-Duser.timezone=Asia/Seoul", "/app.jar"]
