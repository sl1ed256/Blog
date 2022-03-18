package com.example.motya.blog.mapper;

import com.example.motya.blog.dto.CreatePostDto;
import com.example.motya.blog.entity.PostEntity;

public class CreatePostMapper implements Mapper<CreatePostDto, PostEntity> {

    private static final String IMAGE_FOLDER = "posts/";
    private static final CreatePostMapper INSTANCE = new CreatePostMapper();

    @Override
    public PostEntity mapFrom(CreatePostDto object) {
        return PostEntity.builder()
                .author_id(object.getAuthor_id())
                .title(object.getTitle())
                .post_body(object.getPost_body())
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .build();
    }

    public static CreatePostMapper getInstance() {
        return INSTANCE;
    }
}
