package com.example.motya.blog.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String nickname;
    String email;
    String role;
    String password;
    String about;
    Part image;
}
