package com.example.motya.blog.servlet;

import com.example.motya.blog.exception.ValidationException;
import com.example.motya.blog.service.PostService;
import com.example.motya.blog.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var postId = Integer.valueOf(req.getParameter("postId"));
        req.setAttribute("post", postService.findById(postId));

        req.getRequestDispatcher(JspHelper.getPath("post"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var postId = Integer.valueOf(req.getParameter("postId"));
        try {
            postService.delete(postId);
            resp.sendRedirect(req.getContextPath() + "/user");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
