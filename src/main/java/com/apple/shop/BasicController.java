package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller // 서버 기능으로 만들어주는 어노테이션
public class BasicController {
    @GetMapping("/")
//    @ResponseBody 리턴에 있는 문자를 그대로 전송하는 어노테이션
    String hello(){
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String about() {
        return "애플 코딩 스프링부트 웹서버";
    }
}
