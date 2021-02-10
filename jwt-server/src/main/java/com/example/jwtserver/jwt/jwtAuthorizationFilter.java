package com.example.jwtserver.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtserver.config.auth.PrincipalDetails;
import com.example.jwtserver.model.User;
import com.example.jwtserver.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//시큐리티가 filter를 가지고 있는데 그 필터 중에 BasicAuthenticationFilter라는것이 있음
//권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 탐
//권한이 필요없으면 안탐
public class jwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;
    public jwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository=userRepository;
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨.");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokens=request.getHeader("Authorization");
        System.out.println("jwtHeader: "+tokens);
        if(tokens==null||!tokens.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }
        String token=request.getHeader("Authorization").replace("Bearer ","");
        String username= JWT.require(Algorithm.HMAC512("jjm")).build().verify(token).getClaim("username").asString();
        if(username!=null){
            User userEntity=userRepository.findByUsername(username);
            PrincipalDetails principalDetails=new PrincipalDetails(userEntity);
            Authentication authentication=new UsernamePasswordAuthenticationToken(principalDetails,null, principalDetails.getAuthorities());
            //SecurityContextHolder: 세션 공간
            //강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
        }
    }
}
