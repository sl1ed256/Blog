package com.example.motya.blog.mapper;

import com.example.motya.blog.dto.CreateUserDto;
import com.example.motya.blog.entity.RoleEnum;
import com.example.motya.blog.entity.UserEntity;

public class CreateUserMapper implements Mapper<CreateUserDto, UserEntity> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public UserEntity mapFrom(CreateUserDto object) {
        return UserEntity.builder()
                .nickname(object.getNickname())
                .email(object.getEmail())
                .role(RoleEnum.valueOf(object.getRole()))
                .password(object.getPassword())
                .about(object.getAbout())
                .build();

    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
