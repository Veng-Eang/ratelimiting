package com.vengeang.learnredis.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vengeang.learnredis.service.RateLimitingService;

import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private RateLimitingService rateLimitingService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/v1")) {
            String tenantId = request.getHeader("X-Tenant");
            if (StringUtils.hasText(tenantId)) {
                Bucket bucket = rateLimitingService.resolveBucket(tenantId);
                if (bucket.tryConsume(1)) {
                    filterChain.doFilter(request, response);
                } else {
                    sendErrorReponse(response, HttpStatus.TOO_MANY_REQUESTS.value());
                }
            }else{
                sendErrorReponse(response, HttpStatus.FORBIDDEN.value());
            }

        }else{
            filterChain.doFilter(request, response);
        }
    }

    private void sendErrorReponse(HttpServletResponse response, int value) {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(value);

        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
