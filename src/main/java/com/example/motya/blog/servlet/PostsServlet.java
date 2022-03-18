package com.example.motya.blog.servlet;

import com.example.motya.blog.service.PostService;
import com.example.motya.blog.util.JspHelper;
import com.example.motya.blog.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.POSTS)
public class PostsServlet extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", postService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("posts"))
                .forward(req, resp);

    }
}
