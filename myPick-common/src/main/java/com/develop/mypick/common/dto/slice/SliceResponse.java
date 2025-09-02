package com.develop.mypick.common.dto.slice;

import org.springframework.data.domain.Slice;

import java.util.List;

public record SliceResponse<T>(
        List<T> data,
        boolean hasNext, // 다음 페이지 존재 여부
        int page,       // 현재 페이지
        int size       // 요청 사이즈
) {
    public static <T> SliceResponse<T> from(Slice<T> slice) {
        return new SliceResponse<T>(
                slice.getContent(),
                slice.hasNext(),
                slice.getNumber(),
                slice.getSize()
        );
    }
}
