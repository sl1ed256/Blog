package com.example.motya.blog.servlet;

import com.example.motya.blog.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список пользователей</h1>");
            printWriter.write("<ul>");
            userService.findAll().forEach(userDto -> {
                printWriter.write("<li>");
                printWriter.write(String.format("<a href=/Blog/posts?userId=%d>%s</a>", userDto.getId(), userDto.getNickname()));
                printWriter.write("</li>");
            });
            printWriter.write("</ul>");
        }
    }
}
