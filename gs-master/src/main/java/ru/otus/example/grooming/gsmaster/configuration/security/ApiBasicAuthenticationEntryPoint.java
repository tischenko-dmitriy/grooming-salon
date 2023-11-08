package ru.otus.example.grooming.gsmaster.configuration.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Component
public class ApiBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException {
        String authorization = request.getHeader("Authorization");
        String responseBody = "{\"success\":\"false\", \"error\":{\"code\":40100, \"type\":\"AUTHORIZATION_ERROR\", \"message\":\"Unauthorized: " + authorization + "\"}}";
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.println(responseBody);
        try {
            String stringBuilder =
                    "API-METHOD: Authentication" +
                            " " +
                            "REQUEST: " +
                            request.getMethod() +
                            " " +
                            request.getRequestURI() +
                            (Objects.nonNull(request.getQueryString()) ? "?" + request.getQueryString() : "") +
                            " ADDRESS: " +
                            request.getHeader("X-Forwarded-For") +
                            " RESPONSE: " +
                            response.getStatus() +
                            " BODY: " +
                            responseBody +
                            " LEVEL: 2";
            logger.info(stringBuilder);
        } catch (Exception ignore) {
        }
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("ru.otus");
        super.afterPropertiesSet();
    }
}