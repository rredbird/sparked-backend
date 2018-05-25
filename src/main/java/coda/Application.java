package coda;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    
    private void startApplication(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     *
     */
    private void writePropertiesFile() {
        OutputStream propertiesOutputStream = null;
        Properties properties = new Properties();
        
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
    private void readPropertiesFiles() {
        Properties properties = new Properties();
        InputStream propertiesInputStream = null;

        try {
            input = new FileInputStream("config.properties");

            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Program start function. Starts the Spring application
     */
    public static void main(String[] args) {
        Application application = new Application();

        application.startApplication(args);

        application.writePropertiesFile();

    }
}
