package coda.shared.interceptors;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

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
public class ExceptionFilter implements Filter {
    @Autowired
    private ILogging log;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
  
        try {
            chain.doFilter(request, response);
        } catch (Exception exception) {
            HttpServletRequest req = (HttpServletRequest) request;

            String queryString = "";
            if(req.getQueryString() != null) {
                queryString = "?" + req.getQueryString();
            }
            log.error("Request with exception: " + req.getMethod() + " " + req.getRequestURI() + queryString);
            log.exception(exception);
        }
    }
}