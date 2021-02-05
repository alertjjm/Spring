package com.example.springsecuritypractice.config.auth;

//security가 /login 낚아채서 로그인을 진행하는데, 로그인이 완료가되면 시큐리티 sesison을 만들어줌

import com.example.springsecuritypractice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 진행 완료가 되면 시큐리티 session을 만들어 준다(Security ContextHolder)
//오브젝트 => Authentication 타입의 객체
//Authentication 안에 user 정보가 있어야 됨
//User 오브젝트의 타입=> UserDetails 타입 객체

//시큐리티가 가지고 있는 세션 => Authentication => UserDetails
public class PrincipalDetails implements UserDetails {
    private User user;
    public PrincipalDetails(User user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection=new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
