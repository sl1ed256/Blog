package com.example.motya.blog.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePostDto {

    Integer author_id;
    String title;
    String post_body;
    Part image;

}
