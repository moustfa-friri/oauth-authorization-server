package com.baeldung.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class SuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("om success " + request.getPathInfo());
        System.out.println(session.getAttribute("sess"));

        System.out.println("om success " + response.getHeaderNames());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}