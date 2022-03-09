package com.example.motya.blog.dao;

import com.example.motya.blog.entity.PostEntity;
import com.example.motya.blog.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PostDao implements Dao<Integer, PostEntity> {

    public static final PostDao INSTANCE = new PostDao();

    private static final String FIND_ALL_BY_USER_ID = "SELECT * FROM posts WHERE author_id = ?";

    @SneakyThrows
    public List<PostEntity> findAllByUserId(Integer userId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_USER_ID)) {
            preparedStatement.setObject(1, userId);

            var resultSet = preparedStatement.executeQuery();
            List<PostEntity> posts = new ArrayList<>();
            while (resultSet.next()) {
                posts.add(buildPost(resultSet));
            }
            return posts;
        }
    }

    @Override
    public List<PostEntity> findAll() {
        return null;
    }

    @Override
    public Optional<PostEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(PostEntity entity) {

    }

    @Override
    public PostEntity save(PostEntity entity) {
        return null;
    }

    private PostEntity buildPost(ResultSet resultSet) throws SQLException {
        return new PostEntity(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("author_id", Integer.class),
                resultSet.getObject("title", String.class),
                resultSet.getObject("post_body", String.class),
                resultSet.getObject("date_posted", Timestamp.class).toLocalDateTime()
        );

    }

    public static PostDao getInstance() {
        return INSTANCE;
    }
}
