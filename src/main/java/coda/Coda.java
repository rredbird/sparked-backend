package coda;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;
import coda.datalayer.MongoDatabaseAccess;

@Component
public class Coda {

    @Autowired
    private ILogging log;

    @Autowired
    private Properties properties;

    @Autowired
    private MongoDatabaseAccess dataLayer;

    /**
     *
     */
    private void writePropertiesFile() {
        OutputStream propertiesOutputStream = null;
        java.util.Properties properties = new java.util.Properties();
        
        //Set properties to save here
        //properties.setProperty("mongo_Port", "" + "27017");
        //properties.setProperty("mongo_IP", "127.0.0.1");

        try {
            propertiesOutputStream = new FileOutputStream("application.properties");
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
        log.debug("readProperties...");

        java.util.Properties propertiesLoader = new java.util.Properties();
        InputStream propertiesInputStream = null;

        try {
            URL configURL = Coda.class.getClassLoader().getResource("config.properties");
            
            propertiesLoader.load(configURL.openStream());
            properties.load(propertiesLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void OnStartup() {
        this.readPropertiesFile();
        this.dataLayer.initialize();
    }

    @PreDestroy
    public void OnShutdown() {
        log.debug("OnShutdown");
    }
}
