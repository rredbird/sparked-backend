package coda.shared.interceptors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.ModelAndView;

import coda.shared.logging.ILogging;

public class AccessLogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ILogging log;

    @Override
    public boolean preHandle(
            HttpServletRequest request, 
            HttpServletResponse response,
            Object handler) throws Exception {

        
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handle,
            ModelAndView modelAndView) throws Exception {
        log.debug("Request URL:" + request.getRequestURL().toString());
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        
        log.debug(
            "Request Url: " + 
            request.getRequestURL().toString() + 
            "Execution Time: " + 
            (System.currentTimeMillis() - startTime)
        );
    }
}