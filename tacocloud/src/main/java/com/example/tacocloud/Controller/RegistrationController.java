package com.example.tacocloud.Controller;

import com.example.tacocloud.Model.RegistrationForm;
import com.example.tacocloud.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    public String registerForm(){
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm registrationForm){
        userRepo.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
