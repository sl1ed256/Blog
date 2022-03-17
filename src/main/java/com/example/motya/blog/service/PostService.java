package com.example.motya.blog.service;

import com.example.motya.blog.dao.PostDao;
import com.example.motya.blog.dto.PostDto;
import com.example.motya.blog.entity.PostEntity;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class PostService {

    private static final PostService INSTANCE = new PostService();

    private final PostDao postDao = PostDao.getInstance();

    private PostService() {

    }

    public List<PostDto> findAllByUserId(Integer userId) {
        return postDao.findAllPostsByUserId(userId).stream()
                .map(postEntity -> PostDto.builder()
                        .id(postEntity.getId())
                        .author_id(postEntity.getAuthor_id())
                        .title(postEntity.getTitle())
                        .post_body(postEntity.getPost_body())
                        .build()
                )
                .collect(toList());
    }

    public List<PostDto> findAll() {
        return postDao.findAll().stream()
                .map(postEntity -> PostDto.builder()
                        .id(postEntity.getId())
                        .author_id(postEntity.getAuthor_id())
                        .title(postEntity.getTitle())
                        .post_body(postEntity.getPost_body())
                        .image(postEntity.getImage())
                        .build()
                )
                .collect(toList());
    }

    public Optional<PostEntity> findById(Integer postId) {
        return postDao.findById(postId);
    }

    public static PostService getInstance() {
        return INSTANCE;
    }
}
