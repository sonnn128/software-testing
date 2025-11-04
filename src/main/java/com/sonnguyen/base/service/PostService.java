package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.in.PostDto;
import com.sonnguyen.base.dto.res.PostRes;

public interface PostService {
    PostRes createPost(PostDto postDto);
}
