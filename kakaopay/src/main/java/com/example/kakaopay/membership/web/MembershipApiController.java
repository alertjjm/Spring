package com.example.kakaopay.membership.web;

import com.example.kakaopay.common.CommonErrorDto;
import com.example.kakaopay.common.CommonResponseDto;
import com.example.kakaopay.membership.service.MembershipService;
import com.example.kakaopay.membership.web.dto.request.PutPointDepositRequestDto;
import com.example.kakaopay.membership.web.dto.request.PostMembershipRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class MembershipApiController {
    private final MembershipService membershipService;

    @GetMapping("/api/v1/membership")
    public ResponseEntity<?> findMembership(
            @RequestHeader(value = "X-USER-ID") String userId){
        try{
            return new ResponseEntity<>(membershipService.findMembership(userId), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto =
                    new CommonErrorDto("membership_id must be provided",HttpStatus.BAD_REQUEST.value());
            CommonResponseDto<?> commonResponseDto =
                    new CommonResponseDto<>(false,null, commonErrorDto);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/v1/membership")
    public ResponseEntity<?> registerMembership(
            @RequestHeader(value = "X-USER-ID") String userId,
            @Valid @RequestBody PostMembershipRegisterRequestDto requestDto){
        try{
            CommonResponseDto<?> commonResponseDto=membershipService.registerMembership(userId,requestDto);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto =
                    new CommonErrorDto("membership_id must be provided",HttpStatus.BAD_REQUEST.value());
            CommonResponseDto<?> commonResponseDto =
                    new CommonResponseDto<>(false,null, commonErrorDto);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/v1/membership/{membershipId}")
    public ResponseEntity<?> disableMembership(
            @RequestHeader(value = "X-USER-ID") String userId,
            @PathVariable String membershipId){
        try{
            CommonResponseDto<?> commonResponseDto=
                    membershipService.disableMembership(userId,membershipId);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto =
                    new CommonErrorDto("membership_id must be provided",HttpStatus.BAD_REQUEST.value());
            CommonResponseDto<?> commonResponseDto =
                    new CommonResponseDto<>(false,null, commonErrorDto);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/membership/{membershipId}")
    public ResponseEntity<?> findMembershipDetail(
            @RequestHeader(value = "X-USER-ID") String userId,
            @PathVariable String membershipId){
        try{
            CommonResponseDto<?> commonResponseDto=membershipService.findMembershipDetail(userId,membershipId);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto =
                    new CommonErrorDto("membership_id must be provided",HttpStatus.BAD_REQUEST.value());
            CommonResponseDto<?> commonResponseDto =
                    new CommonResponseDto<>(false,null, commonErrorDto);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/v1/membership/point")
    public ResponseEntity<?> depositPoint(
            @RequestHeader(value = "X-USER-ID") String userId,
            @Valid @RequestBody PutPointDepositRequestDto requestDto){
        try{
            CommonResponseDto<?> commonResponseDto=membershipService.depositPoint(userId,requestDto);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto =
                    new CommonErrorDto("membership_id must be provided",HttpStatus.BAD_REQUEST.value());
            CommonResponseDto<?> commonResponseDto =
                    new CommonResponseDto<>(false,null, commonErrorDto);
            return new ResponseEntity<>(commonResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}