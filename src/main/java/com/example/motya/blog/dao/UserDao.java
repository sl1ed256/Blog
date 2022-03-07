package com.example.motya.blog.dao;

import com.example.motya.blog.entity.RoleEnum;
import com.example.motya.blog.entity.UserEntity;
import com.example.motya.blog.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, UserEntity> {

    public static final UserDao INSTANCE = new UserDao();

    private static final String FIND_ALL = "SELECT * FROM users";

    private UserDao() {

    }

    @Override
    public List<UserEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<UserEntity> userEntities = new ArrayList<>();
            while (resultSet.next()) {
                userEntities.add(buildUser(resultSet));
            }
            return userEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(UserEntity entity) {

    }

    @Override
    public UserEntity save(UserEntity entity) {
        return null;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private UserEntity buildUser(ResultSet resultSet) throws SQLException {
        return new UserEntity(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("nickname", String.class),
                resultSet.getObject("email", String.class),
                RoleEnum.valueOf(resultSet.getObject("role", String.class)),
                resultSet.getObject("password", String.class),
                resultSet.getObject("about", String.class),
                resultSet.getObject("created_at", Timestamp.class).toLocalDateTime()
        );
    }
}
