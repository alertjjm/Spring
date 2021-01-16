package com.example.tacocloud.Service;

import com.example.tacocloud.Model.User;
import com.example.tacocloud.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailService implements UserDetailsService {
    UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("User "+username+"Not Found");
        else
            return user;
    }
}
