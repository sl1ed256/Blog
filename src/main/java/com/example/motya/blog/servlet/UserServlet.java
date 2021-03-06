package com.example.motya.blog.servlet;

import com.example.motya.blog.dto.UserDto;
import com.example.motya.blog.service.UserService;
import com.example.motya.blog.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userId = ((UserDto) req.getSession().getAttribute("user")).getId();
        req.setAttribute("user", userService.findUserById(userId));

        req.getRequestDispatcher(JspHelper.getPath("user"))
                .forward(req, resp);
    }
}
