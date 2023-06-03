package com.baeldung.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    private HttpSession session;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        double random=Math.random();
        String bareer=request.getHeader("Authorization");
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        if(session.getAttribute("sess")==null)
            session.setAttribute("sess", random);

        System.out.println(session.getAttribute("sess"));

         filterChain.doFilter(request,response);
    }
}
