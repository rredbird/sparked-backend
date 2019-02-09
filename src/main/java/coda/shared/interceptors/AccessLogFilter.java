package coda.shared.interceptors;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import coda.shared.logging.ILogging;

@Component
@Order(1)
public class AccessLogFilter implements Filter {
    @Autowired
    private ILogging log;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
  
        long requestTime = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest) request;

        String queryString = "";
        if(req.getQueryString() != null) {
            queryString = "?" + req.getQueryString();
        }
        log.trace("Incoming Request: " + req.getMethod() + " " + req.getRequestURI() + queryString);
  
        chain.doFilter(request, response);

        requestTime = System.currentTimeMillis() - requestTime;
        log.trace("Request took " + requestTime + "ms to run.");
    }
}