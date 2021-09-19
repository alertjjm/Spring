package com.example.kakaopay.membership.web.dto.response;

import com.example.kakaopay.membership.domain.Membership;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostMembershipRegisterDto {
    private Long seq;
    private String membershipId;
    private String membershipName;
    private LocalDateTime startDate;
    private String membershipStatus;
    private int point;

    @Builder
    public PostMembershipRegisterDto(Long seq, String membershipId, String membershipName, LocalDateTime startDate, String membershipStatus, int point){
        this.seq=seq;
        this.membershipId=membershipId;
        this.membershipName=membershipName;
        this.startDate=startDate;
        this.membershipStatus=membershipStatus;
        this.point=point;
    }
}
