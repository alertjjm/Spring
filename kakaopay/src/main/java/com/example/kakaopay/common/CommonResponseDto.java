package com.example.kakaopay.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponseDto<T> {
    private boolean success;
    private T response;
    private CommonErrorDto error;
}
