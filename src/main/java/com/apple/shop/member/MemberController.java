package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/member")
    public String addMember(@ModelAttribute Member member) throws Exception {
        if(memberService.addMember(member) != null){
           return "redirect:/list";
        } else {
           return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        if(auth.isAuthenticated()){
            return "mypage.html";
        } else {
            return "redirect:/login";
        }
    }
}
