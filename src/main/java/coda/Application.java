package coda;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import coda.shared.logging.Logging;
import coda.shared.properties.Properties;

@SpringBootApplication
public class Application {
    @Autowired
    private Properties properties;

    @Autowired
    private Logging log;
    
    private void startApplication(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     *
     */
    private void writePropertiesFile() {
        OutputStream propertiesOutputStream = null;
        java.util.Properties properties = new java.util.Properties();
        
        properties.setProperty("mongo_Port", "27017");

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
        log.debug("readProperties...");

        java.util.Properties propertiesLoader = new java.util.Properties();
        InputStream propertiesInputStream = null;

        try {
            propertiesInputStream = new FileInputStream("config.properties");

            propertiesLoader.load(propertiesInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Program start function. Starts the Spring application
     */
    public static void main(String[] args) {
        Application application = new Application();

        application.readPropertiesFile();

        application.startApplication(args);

        application.writePropertiesFile();
    }
}
