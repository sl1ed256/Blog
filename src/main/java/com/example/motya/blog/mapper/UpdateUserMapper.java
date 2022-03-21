package com.example.motya.blog.mapper;

import com.example.motya.blog.dto.UpdateUserDto;
import com.example.motya.blog.entity.UserEntity;

public class UpdateUserMapper implements Mapper<UpdateUserDto, UserEntity> {

    private static final UpdateUserMapper INSTANCE = new UpdateUserMapper();
    private static final String IMAGE_FOLDER = "users/";

    @Override
    public UserEntity mapFrom(UpdateUserDto object) {
        return UserEntity.builder()
                .id(object.getId())
                .nickname(object.getNickname())
                .about(object.getAbout())
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .build();
    }

    public static UpdateUserMapper getInstance() {
        return INSTANCE;
    }
}
