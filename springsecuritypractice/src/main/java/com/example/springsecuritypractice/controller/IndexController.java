package com.example.springsecuritypractice.controller;

import com.example.springsecuritypractice.model.User;
import com.example.springsecuritypractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping({"","/"})
    public String index(){
        return "index";
    }
    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "유저 페이지입니다.";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "어드민 페이지입니다.";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "매니저 페이지입니다.";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }
    @GetMapping("/loginProc")
    public String loginProc() {
        return "joinProc";
    }
    @PostMapping("/join")
    public String join(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println(user);
        return "redirect:/loginForm";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    public String info(){
        return "개인정보";
    }
}

