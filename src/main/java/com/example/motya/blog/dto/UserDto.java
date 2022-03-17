package com.example.motya.blog.dto;


import com.example.motya.blog.entity.RoleEnum;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    Integer id;
    String nickname;
    String image;
    String email;
    RoleEnum role;
    String about;
}
