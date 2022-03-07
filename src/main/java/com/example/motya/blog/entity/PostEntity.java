package com.example.motya.blog.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class PostEntity {

    private Integer id;
    private Integer author_id;
    private String title;
    private String post_body;
    private LocalDateTime date_posted;

    public PostEntity(Integer id, Integer author_id, String title, String post_body, LocalDateTime date_posted) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.post_body = post_body;
        this.date_posted = date_posted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }

    public LocalDateTime getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(LocalDateTime date_posted) {
        this.date_posted = date_posted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity postEntity = (PostEntity) o;
        return Objects.equals(id, postEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author_id=" + author_id +
                ", title='" + title + '\'' +
                ", post_body='" + post_body + '\'' +
                ", date_posted=" + date_posted +
                '}';
    }
}
