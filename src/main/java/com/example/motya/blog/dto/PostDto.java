package com.example.motya.blog.dto;

import java.util.Objects;

public class PostDto {

    private final Integer id;
    private final Integer author_id;
    private final String title;
    private final String post_body;

    public PostDto(Integer id, Integer author_id, String title, String post_body) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.post_body = post_body;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public String getTitle() {
        return title;
    }

    public String getPost_body() {
        return post_body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", author_id=" + author_id +
                ", title='" + title + '\'' +
                ", post_body='" + post_body + '\'' +
                '}';
    }
}
