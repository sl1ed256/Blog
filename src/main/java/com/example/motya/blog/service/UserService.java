package com.example.motya.blog.service;

import com.example.motya.blog.dao.UserDao;
import com.example.motya.blog.dto.CreateUserDto;
import com.example.motya.blog.dto.UpdateUserDto;
import com.example.motya.blog.dto.UserDto;
import com.example.motya.blog.entity.UserEntity;
import com.example.motya.blog.exception.ValidationException;
import com.example.motya.blog.mapper.CreateUserMapper;
import com.example.motya.blog.mapper.UpdateUserMapper;
import com.example.motya.blog.mapper.UserMapper;
import com.example.motya.blog.validator.CreateUserValidator;
import com.example.motya.blog.validator.UpdateUserValidator;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final UpdateUserMapper updateUserMapper = UpdateUserMapper.getInstance();
    private final UpdateUserValidator updateUserValidator = UpdateUserValidator.getInstance();


    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public void update(UpdateUserDto userDto) {
        var validationResult = updateUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = updateUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.update(userEntity);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);

        return userEntity.getId();
    }


    public boolean delete(Integer userId) {
        return userDao.delete(userId);
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
