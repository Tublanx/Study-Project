package com.example.security.controller;

import com.example.security.entity.Member;
import com.example.security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AppController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/sign")
    public String sign(Model model) {
        model.addAttribute("member", new Member());

        return "sign";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("listUsers", memberRepository.findAll());

        return "users";
    }

    @PostMapping("/signProc")
    public String signProcess(Member member, RedirectAttributes attributes) {
        Member findMember = memberRepository.findById(member.getId());

        if (findMember != null) {
            attributes.addFlashAttribute("error", "아이디가 중복되었습니다.");
            return "redirect:/sign";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String pwd = passwordEncoder.encode(member.getPassword());
        member.setPassword(pwd);
        memberRepository.save(member);

        return "redirect:/";
    }

    @PostMapping("/login_fail")
    public String loginFail(Member member, RedirectAttributes attributes) {
        Member findMember = memberRepository.findById(member.getId());

        if (findMember == null) {
            attributes.addFlashAttribute("error", "회원이 존재하지 않습니다.");
            return "redirect:/login";
        }

        return "/login";
    }

}
