package com.example.kakaopay.membership.service;

import com.example.kakaopay.common.CommonResponseDto;
import com.example.kakaopay.membership.domain.Member;
import com.example.kakaopay.membership.domain.Membership;
import com.example.kakaopay.membership.repository.MemberRepository;
import com.example.kakaopay.membership.repository.MembershipRepository;
import com.example.kakaopay.membership.web.dto.request.PutPointDepositRequestDto;
import com.example.kakaopay.membership.web.dto.request.PostMembershipRegisterRequestDto;
import com.example.kakaopay.membership.web.dto.response.GetMembershipDetailDto;
import com.example.kakaopay.membership.web.dto.response.GetMembershipRetrieveDto;
import com.example.kakaopay.membership.web.dto.response.MembershipKernelDto;
import com.example.kakaopay.membership.web.dto.response.PostMembershipRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MembershipServiceImpl implements MembershipService{
    private final MemberRepository memberRepository;
    private final MembershipRepository membershipRepository;

    public List<GetMembershipRetrieveDto> findByUserId(String userId){
        Member member=memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id: "+ userId));
        return member.getMembership()
                .stream().map(membership -> new GetMembershipRetrieveDto(membership, member))
                .collect(Collectors.toList());
    }

    public CommonResponseDto<List<GetMembershipRetrieveDto>> findMembership(String userId){
        List<GetMembershipRetrieveDto> getMembershipRetrieveDtoList = findByUserId(userId);
        return new CommonResponseDto<>(true, getMembershipRetrieveDtoList,null);
    }

    public CommonResponseDto<PostMembershipRegisterDto> registerMembership(String userId, PostMembershipRegisterRequestDto requestDto){
        Member member=memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id: "+ userId));
        Membership membership=requestDto.toEntity();
        membership.setUserId(member.getId());
        Membership savedMembership=membershipRepository.save(membership);
        member.getMembership().add(savedMembership);
        memberRepository.save(member);
        PostMembershipRegisterDto responseDto=
                PostMembershipRegisterDto.builder()
                        .seq(membership.getSeq())
                        .membershipId(membership.getMembershipId())
                        .membershipName(membership.getMembershipName())
                        .startDate(membership.getStartDate())
                        .membershipStatus(membership.getMembershipStatus())
                        .point(membership.getPoint())
                        .build();
        return new CommonResponseDto<>(true, responseDto, null);
    }

    public CommonResponseDto<Boolean> disableMembership(String userId, String membershipId){
        Member member=memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id: "+ userId));
        Optional<Membership> optionalMembership=membershipRepository.findByMembershipId(membershipId);
        if(optionalMembership.isPresent()){
            optionalMembership.get().setMembershipStatus("N");
            return new CommonResponseDto<>(true, true,null);
        }
        else
            return new CommonResponseDto<>(true,false,null);
    }

    public CommonResponseDto<GetMembershipDetailDto> findMembershipDetail(String userId, String membershipId){
        Member member=memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id: "+ userId));
        Membership membership=membershipRepository.findByMembershipId(membershipId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버십이 없습니다. id: "+ membershipId));
        return new CommonResponseDto<>(true,
                new GetMembershipDetailDto(new MembershipKernelDto(membership)),
                null);
    }

    public CommonResponseDto<Boolean> depositPoint(String userId, PutPointDepositRequestDto requestDto){
        Member member=memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id: "+ userId));
        Optional<Membership> optionalMembership=membershipRepository.findByMembershipId(requestDto.getMembershipId());
        if(optionalMembership.isPresent()){
            Membership membership=optionalMembership.get();
            membership.setPoint(membership.getPoint()+requestDto.getAmount());
            return new CommonResponseDto<>(true, true,null);
        }
        else
            return new CommonResponseDto<>(true,false,null);
    }
}
