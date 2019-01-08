package coda.shared.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import coda.shared.interceptors.AccessLogInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
   @Autowired
   AccessLogInterceptor accessLogInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      System.out.println("Interceptor constructor 1");
      registry.addInterceptor(accessLogInterceptor);
      int i = 0;
      System.out.println("Interceptor constructor");
   }
}