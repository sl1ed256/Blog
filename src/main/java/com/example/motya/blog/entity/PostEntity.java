package com.example.motya.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {

    private Integer id;
    private Integer author_id;
    private String title;
    private String post_body;
    private LocalDateTime date_posted;
}
