package com.example.kakaopay.membership.web.dto.response;

import lombok.Getter;

@Getter
public class GetMembershipDetailDto {
    private MembershipKernelDto membership;
    public GetMembershipDetailDto(MembershipKernelDto membershipKernelDto){
        this.membership=membershipKernelDto;
    }
}
