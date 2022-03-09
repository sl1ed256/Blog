package com.example.motya.blog.service;

import com.example.motya.blog.dao.UserDao;
import com.example.motya.blog.dto.UserDto;
import com.example.motya.blog.entity.UserEntity;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .nickname(user.getNickname())
                        .build()
                )
                .collect(toList());
    }

    public Optional<UserEntity> findUserById(Integer userId){
        return userDao.findById(userId);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
