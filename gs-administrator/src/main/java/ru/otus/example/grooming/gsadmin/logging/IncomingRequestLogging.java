package ru.otus.example.grooming.gsadmin.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.otus.example.grooming.gsadmin.configuration.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class IncomingRequestLogging implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        Date startDate = new Date();
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        Date endDate = new Date();
        String requestUri = contentCachingRequestWrapper.getRequestURI();

        if (requestUri.contains(Constants.APPLICATION_USER_TYPE)) {
            logger.info(fillIncomingRequestTemplate(startDate, endDate, contentCachingRequestWrapper, contentCachingResponseWrapper));
        }

        contentCachingResponseWrapper.copyBodyToResponse();

    }

    private String fillIncomingRequestTemplate(Date startDate,
                                               Date endDate,
                                               ContentCachingRequestWrapper contentCachingRequestWrapper,
                                               ContentCachingResponseWrapper contentCachingResponseWrapper) {
        final String LOG_TEMPLATE =
                "START-AT: %s;TRANSACT-ID: %s;SOURCE-APP: %s;REQUEST-METHOD: %s;URI: %s;REQUEST-BODY: %s;RESPONSE-STATUS: %s;RESPONSE-BODY: %s;WORK-TIME: %s";

        String transactId = contentCachingResponseWrapper.getHeader("Transact-Id");
        transactId = Objects.isNull(transactId) ? "-" : transactId;
        String sourceApp = contentCachingResponseWrapper.getHeader("Source-App");
        sourceApp = Objects.nonNull(sourceApp) ? sourceApp : "-";
        String requestURI =
                contentCachingRequestWrapper.getRequestURI() +
                        (StringUtils.isNoneBlank(contentCachingRequestWrapper.getQueryString()) ?
                                "?" + contentCachingRequestWrapper.getQueryString() : "");

        String requestBody =
                new String(contentCachingRequestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        String responseBody =
                new String(contentCachingResponseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

        return String.format(LOG_TEMPLATE,
                simpleDateFormat.format(startDate),
                transactId,
                sourceApp,
                contentCachingRequestWrapper.getMethod(),
                requestURI,
                requestBody,
                contentCachingResponseWrapper.getStatus(),
                responseBody,
                endDate.getTime() - startDate.getTime()
            );
    }

}
