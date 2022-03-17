package com.example.motya.blog.dao;

import com.example.motya.blog.entity.RoleEnum;
import com.example.motya.blog.entity.UserEntity;
import com.example.motya.blog.exception.DaoException;
import com.example.motya.blog.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, UserEntity> {

    public static final UserDao INSTANCE = new UserDao();

    private static final String FIND_ALL_SQL = "SELECT id, nickname, email, role, password, about, created_at, image FROM users";
    private static final String FIND_BY_ID_SQL = "SELECT id, nickname, email, role, password, about, created_at, image FROM users WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
    public static final String UPDATE_SQL = "UPDATE users SET nickname = ?, email = ?, role = ?, password = ?, about = ?" +
            "where id = ?";
    public static final String SAVE_SQL = "INSERT INTO users (nickname, email, role, password, about, image)  " +
            "VALUES (?,?,?,?,?,?)";
    private static final String GET_BY_EMAIL_AND_PASSWORD = "SELECT id, nickname, email, role, password, about, created_at, image FROM users where email = ? and password = ?";

    @SneakyThrows
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            var resultSet = preparedStatement.executeQuery();
            UserEntity user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }

    }

    @SneakyThrows
    @Override
    public List<UserEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
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
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            UserEntity userEntity = null;
            if (resultSet.next()) {
                userEntity = buildUser(resultSet);
            }
            return Optional.ofNullable(userEntity);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getNickname());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getRole().toString());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getAbout());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @SneakyThrows
    @Override
    public UserEntity save(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getNickname());
            preparedStatement.setObject(2, entity.getEmail());
            preparedStatement.setObject(3, entity.getRole().name());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getAbout());
            preparedStatement.setObject(6, entity.getImage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
            return entity;

        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private UserEntity buildUser(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .nickname(resultSet.getObject("nickname", String.class))
                .email(resultSet.getObject("email", String.class))
                .role(RoleEnum.valueOf(resultSet.getObject("role", String.class)))
                .password(resultSet.getObject("password", String.class))
                .about(resultSet.getObject("about", String.class))
                .created_at(resultSet.getObject("created_at", Timestamp.class).toLocalDateTime())
                .image(resultSet.getObject("image", String.class))
                .build();
    }
}
