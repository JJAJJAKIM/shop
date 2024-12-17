package com.apple.shop.member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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
        MemberDTO data = memberMapper.memberToMemberDTO(a.orElseThrow(()-> new RuntimeException("그런 유저없다.")));
        return data;
    }

    @PostMapping("/login/jwt")
    @ResponseBody
    public String loginJWT(@RequestBody Map<String, String> data , HttpServletResponse response){

        var authToken = new UsernamePasswordAuthenticationToken(data.get("username"), data.get("password"));
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(auth);
        var jwt = JwtUtil.createToken(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(jwt);

        var cookie = new Cookie("jwt",jwt);
        cookie.setMaxAge(10);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // 쿠키가 전송될 경로
        response.addCookie(cookie);

        return jwt;
    }

    @GetMapping("/my-page/jwt")
    @ResponseBody
    public String myPageJWT(){

        return "mypage-jwt";
    }
}

