package com.example.motya.blog.servlet;

import com.example.motya.blog.dto.CreateUserDto;
import com.example.motya.blog.entity.RoleEnum;
import com.example.motya.blog.exception.ValidationException;
import com.example.motya.blog.service.UserService;
import com.example.motya.blog.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", List.of("user", "admin"));
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userDto = CreateUserDto.builder()
                .nickname(req.getParameter("username"))
                .email(req.getParameter("email"))
                .role(req.getParameter("role"))
                .password(req.getParameter("password"))
                .about(req.getParameter("about"))
                .image(req.getPart("image"))
                .build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
