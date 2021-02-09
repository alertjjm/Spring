package com.example.jwtserver.config.auth;

import com.example.jwtserver.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class PrincipalDetails implements UserDetails {
    private User user;
    public PrincipalDetails(User user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        user.getRoleList().forEach(u->{authorities.add(()->u);});
        //List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
        //              .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
        //              .collect(Collectors.toList()); 이렇게로도 표현가능
        return null;
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
