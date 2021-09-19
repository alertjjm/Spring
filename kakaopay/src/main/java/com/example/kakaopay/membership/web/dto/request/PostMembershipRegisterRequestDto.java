package com.example.kakaopay.membership.web.dto.request;

import com.example.kakaopay.membership.domain.Membership;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
public class PostMembershipRegisterRequestDto {
    @NotBlank(message = "멤버십 아이디를 입력해주세요.")
    @Size(min=1,max=250,message = "멤버십 아이디를 다시 확인해주세요.")
    private String membershipId;

    @NotBlank(message = "멤버십 이름을 입력해주세요.")
    @Size(min=1,max=250,message = "멤버십 이름을 다시 확인해주세요.")
    private String membershipName;

    @NotNull(message = "멤버십 포인트를 입력해주세요.")
    @Min(value = 0, message = "0보다 큰 멤버십 포인트를 입력해주세요.")
    @Max(value = Integer.MAX_VALUE,message = "적절한 멤버십 포인트를 입력해주세요.")
    private int point;

    @Builder
    public PostMembershipRegisterRequestDto(String membershipId, String membershipName, int point){
        this.membershipId=membershipId;
        this.membershipName=membershipName;
        this.point=point;
    }

    public Membership toEntity(){
        return Membership.builder()
                .membershipId(membershipId)
                .membershipName(membershipName)
                .membershipStatus("Y")
                .point(point)
                .startDate(LocalDateTime.now())
                .build();
    }
}
