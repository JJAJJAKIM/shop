package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @GetMapping("/register")
    public String register(Authentication auth) {
        if (auth.isAuthenticated()) {
            return "redirect:/list";
        }
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my-page")
    public String myPage(Authentication auth, Principal principal) {
        CustomUser result = (CustomUser)auth.getPrincipal();
        System.out.println(result.displayName);
            return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDTO getMember() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        var data = new MemberDTO(result.getId(), result.getUsername(),result.getDisplayname());
        return data;
    }
}

