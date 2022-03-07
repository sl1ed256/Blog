package com.example.motya.blog.dto;

import java.util.Objects;

public class UserDto {

    private final Integer id;
    private final String nickname;

    public UserDto(Integer id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
