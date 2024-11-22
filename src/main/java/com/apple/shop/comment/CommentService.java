package com.apple.shop.comment;

import com.apple.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Long addComment(String comment, Long parentId, Authentication auth) {
        CustomUser user = (CustomUser) auth.getPrincipal();
        var data = new Comment();
        data.setContent(comment);
        data.setUsername(user.getUsername());
        data.setParentId(parentId);
        commentRepository.save(data);
        return data.getParentId();
    }
}
