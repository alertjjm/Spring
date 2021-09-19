package com.example.kakaopay.membership.service;

import com.example.kakaopay.common.CommonResponseDto;
import com.example.kakaopay.membership.web.dto.request.PostMembershipRegisterRequestDto;
import com.example.kakaopay.membership.web.dto.request.PutPointDepositRequestDto;
import com.example.kakaopay.membership.web.dto.response.GetMembershipDetailDto;
import com.example.kakaopay.membership.web.dto.response.GetMembershipRetrieveDto;
import com.example.kakaopay.membership.web.dto.response.PostMembershipRegisterDto;

import java.util.List;

public interface MembershipService {
    public List<GetMembershipRetrieveDto> findByUserId(String userId);

    public CommonResponseDto<List<GetMembershipRetrieveDto>> findMembership(String userId);

    public CommonResponseDto<PostMembershipRegisterDto> registerMembership(String userId, PostMembershipRegisterRequestDto requestDto);

    public CommonResponseDto<Boolean> disableMembership(String userId, String membershipId);

    public CommonResponseDto<GetMembershipDetailDto> findMembershipDetail(String userId, String membershipId);

    public CommonResponseDto<Boolean> depositPoint(String userId, PutPointDepositRequestDto requestDto);
}
