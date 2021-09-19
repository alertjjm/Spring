package com.example.kakaopay.membership.web;

import com.example.kakaopay.membership.domain.Member;
import com.example.kakaopay.membership.domain.Membership;
import com.example.kakaopay.membership.repository.MemberRepository;
import com.example.kakaopay.membership.repository.MembershipRepository;
import com.example.kakaopay.membership.web.dto.request.PutPointDepositRequestDto;
import com.example.kakaopay.membership.web.dto.request.PostMembershipRegisterRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MembershipApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member memberSetup(){
        return memberRepository.save(Member.builder().userId("test1").build());
    }

    private Membership spcSetup(Member savedMember){
        return
        membershipRepository.save(Membership.builder()
                .membershipId("spc")
                .membershipName("happypoint")
                .membershipStatus("Y")
                .point(120)
                .userId(savedMember.getId())
                .startDate(LocalDateTime.parse("2021-06-20T14:48:29.831"))
                .build()
        );
    }

    private Membership shinsegaeSetup(Member savedMember){
        return
        membershipRepository.save(Membership.builder()
                .membershipId("shinsegae")
                .membershipName("shinsegaepoint")
                .membershipStatus("Y")
                .point(3500)
                .userId(savedMember.getId())
                .startDate(LocalDateTime.parse("2021-06-20T14:48:30.011"))
                .build()
        );
    }

    private Membership cjSetup(Member savedMember){
        return
        membershipRepository.save(Membership.builder()
                .membershipId("cj")
                .membershipName("cjone")
                .membershipStatus("N")
                .point(1029)
                .userId(savedMember.getId())
                .startDate(LocalDateTime.parse("2021-06-20T14:48:30.043"))
                .build()
        );
    }

    public void totalSetUp(){
        Member savedMember=memberSetup();
        spcSetup(savedMember);
        shinsegaeSetup(savedMember);
        cjSetup(savedMember);
    }

    @AfterEach
    public void clearAll(){
        membershipRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void 멤버십_전체조회하기_API_성공() throws Exception{
        totalSetUp();
        String url="http://localhost:"+port+"/api/v1/membership";
        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", "test1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].membershipId").value("spc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].userId").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].membershipName").value("happypoint"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].startDate").value("2021-06-20T14:48:29.831"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].membershipStatus").value("Y"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].point").value(120))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].membershipId").value("shinsegae"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].userId").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].membershipName").value("shinsegaepoint"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].startDate").value("2021-06-20T14:48:30.011"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].membershipStatus").value("Y"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].point").value(3500))

                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].membershipId").value("cj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].userId").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].membershipName").value("cjone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].startDate").value("2021-06-20T14:48:30.043"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].membershipStatus").value("N"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[2].point").value(1029))
        ;
    }

    @Test
    public void 멤버십_전체조회하기_API_실패() throws Exception{
        totalSetUp();
        String url="http://localhost:"+port+"/api/v1/membership";
        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", ""))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").doesNotExist());
    }

    @Test
    public void 멤버십_등록하기_API_성공() throws Exception{
        memberSetup();
        String url="http://localhost:"+port+"/api/v1/membership";
        PostMembershipRegisterRequestDto requestDto=PostMembershipRegisterRequestDto.builder()
                .membershipId("cj")
                .membershipName("cjone")
                .point(5210)
                .build();
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", "test1")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.seq").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.membershipId").value("cj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.membershipName").value("cjone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.startDate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.point").value(5210));
    }

    @Test
    public void 멤버십_삭제하기_API_성공() throws Exception{
        Member savedMember=memberSetup();
        Membership savedMemberhsip=shinsegaeSetup(savedMember);
        String url="http://localhost:"+port+"/api/v1/membership/"+savedMemberhsip.getMembershipId();
        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", "test1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value(true));
    }

    @Test
    public void 멤버십_상세_조회하기_API_성공() throws Exception{
        Member savedMember=memberSetup();
        Membership savedMemberhsip=spcSetup(savedMember);
        String url="http://localhost:"+port+"/api/v1/membership/"+savedMemberhsip.getMembershipId();
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", "test1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.membership.membershipId").value("spc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.membership.membershipName").value("happypoint"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.membership.startDate").value("2021-06-20T14:48:29.831"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.membership.membershipStatus").value("Y"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.membership.point").value(120));
    }

    @Test
    public void 포인트_적립하기_API_성공() throws Exception{
        Member savedMember=memberSetup();
        cjSetup(savedMember);
        String url="http://localhost:"+port+"/api/v1/membership/point";
        PutPointDepositRequestDto requestDto= PutPointDepositRequestDto.builder()
                .membershipId("cj")
                .amount(100)
                .build();
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", "test1")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").exists());
    }
}
