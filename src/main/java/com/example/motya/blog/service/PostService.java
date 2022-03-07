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
                .map(postEntity -> new PostDto(
                        postEntity.getId(),
                        postEntity.getAuthor_id(),
                        postEntity.getTitle(),
                        postEntity.getPost_body()
                ))
                .collect(toList());
    }

    public static PostService getInstance() {
        return INSTANCE;
    }
}
