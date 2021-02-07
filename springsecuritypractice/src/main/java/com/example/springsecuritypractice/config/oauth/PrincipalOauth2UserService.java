package com.example.springsecuritypractice.config.oauth;

import com.example.springsecuritypractice.config.auth.PrincipalDetails;
import com.example.springsecuritypractice.model.User;
import com.example.springsecuritypractice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest: "+super.loadUser(userRequest).getAttributes());
        OAuth2User oAuth2User=super.loadUser(userRequest);
        String provider=userRequest.getClientRegistration().getRegistrationId();
        String providerId=oAuth2User.getAttribute("sub");
        String username=provider+"_"+providerId;
        String password=bCryptPasswordEncoder.encode("겟인데어");
        String email=oAuth2User.getAttribute("email");
        String role="ROLE_USER";
        User userEntity=userRepository.findByUsername(username);
        if(userEntity==null){
            userEntity=User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }
        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }
}
