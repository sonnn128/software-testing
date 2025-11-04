package com.sonnguyen.base.service.impl;

import com.sonnguyen.base.dto.in.PostDto;
import com.sonnguyen.base.dto.res.PostRes;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.service.AuthService;
import com.sonnguyen.base.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final AuthService authService;

    @Override
    public PostRes createPost(PostDto postDto) {
        User user = authService.getCurrentUser();
        return PostRes.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .userName(user.getUsername())
                .build();
    }
}
