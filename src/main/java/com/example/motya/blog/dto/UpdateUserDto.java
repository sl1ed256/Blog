package com.example.motya.blog.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateUserDto {
    Integer id;
    String nickname;
    String about;
    Part image;
}
