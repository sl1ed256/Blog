package com.example.motya.blog.service;

import com.example.motya.blog.dao.UserDao;
import com.example.motya.blog.dto.CreateUserDto;
import com.example.motya.blog.dto.UserDto;
import com.example.motya.blog.entity.UserEntity;
import com.example.motya.blog.exception.ValidationException;
import com.example.motya.blog.mapper.CreateUserMapper;
import com.example.motya.blog.validator.CreateUserValidator;
import com.example.motya.blog.validator.ValidationResult;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();
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

    public Optional<UserEntity> findUserById(Integer userId) {
        return userDao.findById(userId);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
