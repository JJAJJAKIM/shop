package com.apple.shop.notice;

import com.apple.shop.Age;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/notice")
    String notice(Model model){
        List<Notice> list =  noticeRepository.findAll();
        model.addAttribute("noti",list);
        System.out.println(list.toString());
        var a = new Age();
        a.setName("홍길동");
        a.setAge(60);
        a.나이설정(20);
        a.더하기();
        System.out.println(a.getAge());
        return "notice.html";
    }

}
