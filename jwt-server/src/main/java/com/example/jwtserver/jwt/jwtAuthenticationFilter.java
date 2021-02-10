package com.example.jwtserver.jwt;

import com.example.jwtserver.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class jwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1.username, password 받아서
        ObjectMapper om=new ObjectMapper();
        try {
            User user=om.readValue(request.getInputStream(),User.class);
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            Authentication authentication=authenticationManager.authenticate(token);
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2. 정상인지 로그인 시도를 해봄, authenticationManager로 로그인 시도를 하면 Principal Details Service가 호출 loadbyusername 실행

        //3. principaldetails를 세션에 담고 ->권한관리를 위해

        //4. jwt토큰을 만들어서 응답
        return null;
    }
}
