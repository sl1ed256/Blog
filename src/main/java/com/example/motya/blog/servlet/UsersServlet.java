package com.example.motya.blog.servlet;

import com.example.motya.blog.exception.ValidationException;
import com.example.motya.blog.service.UserService;
import com.example.motya.blog.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("users"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userId = Integer.valueOf(req.getParameter("userId"));
        try {
            userService.delete(userId);
            resp.sendRedirect(req.getContextPath() + "/users");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
