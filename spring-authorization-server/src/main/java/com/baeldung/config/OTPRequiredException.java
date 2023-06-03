package com.baeldung.config;

import org.springframework.security.core.AuthenticationException;

public class OTPRequiredException extends AuthenticationException {

    public OTPRequiredException(String msg) {
            super(msg);
        }

    public OTPRequiredException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }


