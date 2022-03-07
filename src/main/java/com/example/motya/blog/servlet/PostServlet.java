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
public class PostServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userId = Integer.valueOf(req.getParameter("userId"));

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Статьи пользователя </h1>");
            printWriter.write("<ul>");
            postService.findAllByUserId(userId).forEach(postDto -> {
                printWriter.write("<li>");
                printWriter.write(postDto.getTitle());
                printWriter.write("</li>");

            });
            printWriter.write("</ul>");

        }
    }
}
