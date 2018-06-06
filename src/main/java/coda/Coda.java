package coda;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import coda.shared.logging.Logging;
import coda.shared.properties.Properties;
import coda.database.DataLayer;

@Component
public class Coda {

    @Autowired
    private Logging log;

    @Autowired
    private Properties properties;

    @Autowired
    private DataLayer dataLayer;

    /**
     *
     */
    private void writePropertiesFile() {
    OutputStream propertiesOutputStream = null;
        java.util.Properties properties = new java.util.Properties();
        
        properties.setProperty("mongo_Port", "32768");

        try {
            propertiesOutputStream = new FileOutputStream("config.properties");
            properties.store(propertiesOutputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                propertiesOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
    }

    /**
     *
     */
    private void readPropertiesFile() {
        try {
            properties.loadPropertiesFrom(new FileInputStream("config.properties"));
            log.debug("readProperties...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void OnStartup() {
        log.debug("OnStartup");
        this.readPropertiesFile();
        this.dataLayer.initialize();
        log.debug("TEST");
    }

    @PreDestroy
    public void OnShutdown() {
        log.debug("OnShutdown");
    }
}
