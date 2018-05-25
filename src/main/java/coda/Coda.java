package coda;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import coda.shared.logging.Logging;
import coda.shared.properties.Properties;

@Component
public class Coda {

    @PostConstruct
    public void Startup() {
        System.out.println("TEST");
        System.out.println("TEST");
        System.out.println("TEST");
        System.out.println("TEST");
        System.out.println("TEST");
        System.out.println("TEST");
        System.out.println("TEST");
        System.out.println("TEST");
        System.out.println("TEST");
    }
}
