package com.apple.shop.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class CommentController {

    @PostMapping("/addComment")
    public String addComment(Comment comment, Authentication authentication ) {
        return "redirect:/detail/"+ comment.getParentId();
    }
}
