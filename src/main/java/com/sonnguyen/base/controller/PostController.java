package com.sonnguyen.base.controller;


import com.sonnguyen.base.dto.in.PostDto;
import com.sonnguyen.base.model.Post;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.dto.res.ApiResponse;
import com.sonnguyen.base.repository.PostRepository;
import com.sonnguyen.base.service.AuthService;
import com.sonnguyen.base.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto req) {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .data(postService.createPost(req))
                        .success(true)
                        .message("create post successfully")
                        .build()
        );
    }
}
