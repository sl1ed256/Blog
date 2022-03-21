package com.example.motya.blog.service;

import com.example.motya.blog.dto.UserDto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {

    private UserService userService;


    @BeforeEach
    void prepare() {
        userService = UserService.getInstance();
    }

    @Test
    void loginSuccessIfUserExists(){
        Optional<UserDto> user = userService.login("ant@gmail.com", "123456");
        assertTrue(user.isPresent());
    }

    @Test
    void logicFailIfPasswordNotCorrect(){
        Optional<UserDto> user = userService.login("ant@gmail.com", "12345");
        assertTrue(user.isEmpty());
    }

    @Test
    void logicFailIfUserDoesNotExist(){
        var maybeUser = userService.login("2332@mail.ru", "222");
        assertTrue(maybeUser.isEmpty());
    }

    @Test
    void usersEmptyIfNoUserAdded() {
        var users = userService.findAll();
        assertFalse(users.isEmpty());
    }

    @Test
    void initialUsersSize() {
        var users = userService.findAll();
        assertEquals(2, users.size());
    }

    @AfterEach
    void deleteDataFromDatabase() {

    }

}
