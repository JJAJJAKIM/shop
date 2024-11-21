package com.apple.shop.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute Comment comment, Authentication auth ) {
        comment.setUsername(auth.getName());
        commentRepository.save(comment);
        return "redirect:/detail/"+ comment.getParentId();
    }
}
