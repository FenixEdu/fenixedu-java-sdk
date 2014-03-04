package org.fenixedu.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fenixedu.sdk.FenixEduClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class FenixEduAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(FenixEduAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        try {
            logger.debug("Redirecting authentication exception to {}", FenixEduClientFactory.getSingleton()
                    .getAuthenticationUrl());
            response.sendRedirect(FenixEduClientFactory.getSingleton().getAuthenticationUrl());
        } catch (Exception e) {

        }

    }
}
