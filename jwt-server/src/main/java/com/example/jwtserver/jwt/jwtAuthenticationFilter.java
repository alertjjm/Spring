package com.example.jwtserver.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtserver.config.auth.PrincipalDetails;
import com.example.jwtserver.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class jwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1.username, password 받아서
        //2. 정상인지 로그인 시도를 해봄, authenticationManager로 로그인 시도를 하면 Principal Details Service가 호출 loadbyusername 실행
        ObjectMapper om=new ObjectMapper();
        try {
            User user=om.readValue(request.getInputStream(),User.class);
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            Authentication authentication=authenticationManager.authenticate(token);
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3. principaldetails를 세션에 담고 ->권한관리를 위해

        //4. jwt토큰을 만들어서 응답
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails=(PrincipalDetails)authResult.getPrincipal();
        String jwtToken= JWT.create()
                .withSubject("mytoken")
                .withExpiresAt(new Date(System.currentTimeMillis()+600000))
                .withClaim("id",principalDetails.getUser().getId())
                .withClaim("username",principalDetails.getUsername())
                .sign(Algorithm.HMAC512("jjm"));
        response.addHeader("Authorization","Bearer "+jwtToken);
    }
}
