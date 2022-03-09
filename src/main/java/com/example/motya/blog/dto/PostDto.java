package com.example.motya.blog.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostDto {

    Integer id;
    Integer author_id;
    String title;
    String post_body;
}
