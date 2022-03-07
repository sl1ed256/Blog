package com.example.motya.blog.service;

import com.example.motya.blog.dao.UserDao;
import com.example.motya.blog.dto.UserDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getNickname()
                ))
                .collect(toList());

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
