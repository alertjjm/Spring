package com.example.kakaopay.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonErrorDto {
    private String message;
    private int status;
}
