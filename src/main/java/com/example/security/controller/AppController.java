package com.example.security.controller;

import com.example.security.entity.Member;
import com.example.security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/sign")
    public String sign(Model model) {
        model.addAttribute("member", new Member());

        return "sign";
    }

    @PostMapping("/signProc")
    public String signProcess(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String pwd = passwordEncoder.encode(member.getPassword());
        member.setPassword(pwd);
        memberRepository.save(member);

        return "redirect:/";
    }

}
