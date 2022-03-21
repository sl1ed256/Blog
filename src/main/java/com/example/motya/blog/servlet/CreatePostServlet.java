package com.example.motya.blog.servlet;

import com.example.motya.blog.dto.CreatePostDto;
import com.example.motya.blog.dto.UserDto;
import com.example.motya.blog.exception.ValidationException;
import com.example.motya.blog.service.PostService;
import com.example.motya.blog.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/newPost")
public class CreatePostServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("newPost"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var createPostDto = CreatePostDto.builder()
                .author_id(((UserDto) req.getSession().getAttribute("user")).getId())
                .title(req.getParameter("title"))
                .post_body(req.getParameter("post_body"))
                .image(req.getPart("image"))
                .build();

        try {
            postService.create(createPostDto);
            resp.sendRedirect(req.getContextPath() + "/posts");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
