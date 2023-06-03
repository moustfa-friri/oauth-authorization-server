package com.baeldung.config;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private HttpSession session;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        if (session.getAttribute("username") == null) {
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();

            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            if (passwordEncoder.matches(password,userDetails.getPassword())) {
                session.setAttribute("username",name);
                return new UsernamePasswordAuthenticationToken(
                        name, userDetails.getPassword(), new ArrayList<>());
            }
            else
                throw new BadCredentialsException("Bad Credentials");

        } else {
            return handleValidateOtp(authentication);
        }


    }

    private UsernamePasswordAuthenticationToken handleValidateOtp(Authentication authentication) {
        String username = session.getAttribute("username").toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String otp = authentication.getCredentials().toString();
        if (otp.equals("1234"))
            return new UsernamePasswordAuthenticationToken(
                    username, userDetails.getPassword(), new ArrayList<>());
        else
            throw new BadCredentialsException("invalid otp");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}