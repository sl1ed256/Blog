package com.example.motya.blog.servlet;

import com.example.motya.blog.dto.UpdateUserDto;
import com.example.motya.blog.dto.UserDto;
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

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("updateUser"))
                .forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var updateUserDto = UpdateUserDto.builder()
                .id(((UserDto) req.getSession().getAttribute("user")).getId())
                .nickname(req.getParameter("username"))
                .about(req.getParameter("about"))
                .image(req.getPart("image"))
                .build();
        try {
            userService.update(updateUserDto);
            resp.sendRedirect(req.getContextPath() + "/user");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }

    }
}
