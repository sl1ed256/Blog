package com.example.motya.blog.service;

import com.example.motya.blog.dao.PostDao;
import com.example.motya.blog.dto.PostDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PostService {

    private static final PostService INSTANCE = new PostService();

    private final PostDao postDao = PostDao.getInstance();

    private PostService() {

    }

    public List<PostDto> findAllByUserId(Integer userId) {
        return postDao.findAllByUserId(userId).stream()
                .map(postEntity -> PostDto.builder()
                        .id(postEntity.getId())
                        .author_id(postEntity.getAuthor_id())
                        .title(postEntity.getTitle())
                        .post_body(postEntity.getPost_body())
                        .build()
                )
                .collect(toList());
    }

    public static PostService getInstance() {
        return INSTANCE;
    }
}
