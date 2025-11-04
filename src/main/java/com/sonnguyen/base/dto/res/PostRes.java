package com.sonnguyen.base.dto.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRes {
    private String title;
    private String content;
    private String userName;
}
