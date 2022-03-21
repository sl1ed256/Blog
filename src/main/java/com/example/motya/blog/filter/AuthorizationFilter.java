package com.example.motya.blog.filter;


import com.example.motya.blog.dto.UserDto;
import com.example.motya.blog.entity.RoleEnum;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.example.motya.blog.util.UrlPath.LOGIN;
import static com.example.motya.blog.util.UrlPath.LOGOUT;
import static com.example.motya.blog.util.UrlPath.POST;
import static com.example.motya.blog.util.UrlPath.POSTS;
import static com.example.motya.blog.util.UrlPath.REGISTRATION;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(REGISTRATION, LOGIN, POSTS, POST, LOGOUT);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            var previousPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(previousPage != null ? previousPage : ((HttpServletRequest) servletRequest).getContextPath() + LOGIN);

        }

    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null && (user.getRole() == RoleEnum.user || user.getRole() == RoleEnum.admin);
    }

    private boolean isPublicPath(String uri) {
        String newUri = uri.replace("/Blog", "");
        return PUBLIC_PATH.stream().anyMatch(newUri::startsWith);
    }
}
