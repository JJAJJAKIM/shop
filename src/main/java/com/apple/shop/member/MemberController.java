package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/new")
    public String newMember() {
        return "new.html";
    }

    @PostMapping("/memberAdd")
    public String memberAdd(@ModelAttribute Member member) {
        memberRepository.save(member);
        System.out.println(member);
        return "redirect:/list";
    }
}
