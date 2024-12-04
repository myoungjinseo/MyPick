package com.develop.mypick.common.dto.slice;

import org.springframework.data.domain.Slice;

import java.util.List;

public record SliceResponse<T>(
        List<T> data,
        SliceData meta
) {
    public static SliceResponse from(Slice slice){
        return new SliceResponse(
                slice.getContent(),
                new SliceData(slice.getNumber(),slice.getSize(),slice.isFirst(),slice.isLast())
        );
    }
}
