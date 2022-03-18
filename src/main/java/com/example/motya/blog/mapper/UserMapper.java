package com.example.motya.blog.mapper;

import com.example.motya.blog.dto.UserDto;
import com.example.motya.blog.entity.UserEntity;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<UserEntity, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(UserEntity object) {
        return UserDto.builder()
                .id(object.getId())
                .nickname(object.getNickname())
                .email(object.getEmail())
                .image(object.getImage())
                .about(object.getAbout())
                .role(object.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
