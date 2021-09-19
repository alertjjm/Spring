package com.example.kakaopay.membership.web.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
public class PutPointDepositRequestDto {
    @NotBlank(message = "멤버십 아이디를 입력해주세요.")
    @Size(min=1,max=250,message = "멤버십 아이디를 다시 확인해주세요.")
    private String membershipId;

    @NotNull(message = "적립할 포인트를 입력해주세요.")
    @Min(value = 0, message = "0보다 큰 멤버십 포인트를 입력해주세요.")
    @Max(value = Integer.MAX_VALUE,message = "적절한 멤버십 포인트를 입력해주세요.")
    private int amount;

    @Builder
    public PutPointDepositRequestDto(String membershipId, int amount){
        this.membershipId=membershipId;
        this.amount=amount;
    }
}
