package com.example.motya.blog.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    Integer id;
    String nickname;
}
