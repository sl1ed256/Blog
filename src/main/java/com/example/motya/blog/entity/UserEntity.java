package com.example.motya.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    private Integer id;
    private String nickname;
    private String email;
    private RoleEnum role;
    private String password;
    private String about;
    private LocalDateTime created_at;
    private String image;
}
