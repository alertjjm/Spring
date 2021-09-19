package com.example.kakaopay.membership.web.dto.response;

import com.example.kakaopay.membership.domain.Member;
import com.example.kakaopay.membership.domain.Membership;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetMembershipRetrieveDto {
    private Long seq;
    private String membershipId;
    private String userId;
    private String membershipName;
    private LocalDateTime startDate;
    private String membershipStatus;
    private int point;

    public GetMembershipRetrieveDto(Membership membership, Member member){
        this.seq=membership.getSeq();
        this.membershipId=membership.getMembershipId();
        this.userId=member.getUserId();
        this.membershipName=membership.getMembershipName();
        this.startDate=membership.getStartDate();
        this.membershipStatus=membership.getMembershipStatus();
        this.point=membership.getPoint();
    }
}
