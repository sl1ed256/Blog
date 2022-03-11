package com.example.motya.blog.servlet;

import com.example.motya.blog.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Все статьи</h1>");
            printWriter.write("<ul>");
            postService.findAll().forEach(postDto -> {
                printWriter.write("<li>");
                printWriter.write(String.format("<a href=/Blog/post?id=%d>%s</a>", postDto.getId(), postDto.getTitle()));
                printWriter.write("</li>");
            });
            printWriter.write("</ul>");
        }

    }
}
