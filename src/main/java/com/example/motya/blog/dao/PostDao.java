package com.example.motya.blog.dao;

import com.example.motya.blog.entity.PostEntity;
import com.example.motya.blog.exception.DaoException;
import com.example.motya.blog.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PostDao implements Dao<Integer, PostEntity> {

    public static final PostDao INSTANCE = new PostDao();

    private static final String FIND_ALL_POSTS_BY_USER_ID_SQL = "SELECT id, author_id, title, post_body, date_posted, image FROM posts " +
            "WHERE author_id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, author_id, title, post_body, date_posted, image FROM posts ORDER BY date_posted DESC";
    private static final String FIND_BY_ID_SQL = "SELECT id, author_id, title, post_body, date_posted, image FROM posts WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM posts WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE posts SET title = ?, post_body = ? WHERE id = ?";
    private static final String SAVE_SQL = "INSERT INTO posts (author_id, title, post_body, image) " +
            "VALUES (?,?,?,?)";


    public List<PostEntity> findAllPostsByUserId(Integer userId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_POSTS_BY_USER_ID_SQL)) {
            preparedStatement.setObject(1, userId);

            var resultSet = preparedStatement.executeQuery();
            List<PostEntity> posts = new ArrayList<>();
            while (resultSet.next()) {
                posts.add(buildPost(resultSet));
            }
            return posts;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<PostEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<PostEntity> postEntities = new ArrayList<>();
            while (resultSet.next()) {
                postEntities.add(buildPost(resultSet));
            }
            return postEntities;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @SneakyThrows
    @Override
    public Optional<PostEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            PostEntity postEntity = null;
            if (resultSet.next()) {
                postEntity = buildPost(resultSet);
            }
            return Optional.ofNullable(postEntity);
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
    public void update(PostEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getPost_body());
            preparedStatement.setInt(3, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }

    }

    @SneakyThrows
    @Override
    public PostEntity save(PostEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getAuthor_id());
            preparedStatement.setObject(2, entity.getTitle());
            preparedStatement.setObject(3, entity.getPost_body());
            preparedStatement.setObject(4, entity.getImage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
            return entity;
        }
    }

    private PostEntity buildPost(ResultSet resultSet) throws SQLException {
        return new PostEntity(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("author_id", Integer.class),
                resultSet.getObject("title", String.class),
                resultSet.getObject("post_body", String.class),
                resultSet.getObject("date_posted", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("image", String.class)
        );

    }

    public static PostDao getInstance() {
        return INSTANCE;
    }
}
