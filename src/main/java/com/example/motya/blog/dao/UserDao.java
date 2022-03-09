package com.example.motya.blog.dao;

import com.example.motya.blog.entity.RoleEnum;
import com.example.motya.blog.entity.UserEntity;
import com.example.motya.blog.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, UserEntity> {

    public static final UserDao INSTANCE = new UserDao();

    private static final String FIND_ALL = "SELECT * FROM users";

    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String DELETE = "DELETE FROM users WHERE id = ?";

    public static final String UPDATE = "UPDATE users SET nickname = ?, email = ?, role = ?, password = ?, about = ?" +
            "where id = ?";

    @SneakyThrows
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
        }
    }

    @SneakyThrows
    @Override
    public Optional<UserEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            UserEntity userEntity = null;
            if (resultSet.next()) {
                userEntity = buildUser(resultSet);
            }
            return Optional.ofNullable(userEntity);
        }
    }

    @SneakyThrows
    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public void update(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getNickname());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getRole().toString());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getAbout());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
