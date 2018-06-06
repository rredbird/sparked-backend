package coda.shared.properties;

import java.util.Arrays;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import coda.shared.logging.Logging;

@Component("properties")
public class Properties {
    private int mongoDatabasePort = 27017;

    @Autowired
    private Logging log;

    public int  getMongoDatabasePort() { return mongoDatabasePort; }
    public void setMongoDatabasePort(int port) { mongoDatabasePort = port; }
    public void setMongoDatabasePort(String port) { mongoDatabasePort = Integer.parseInt(port); }

    public void loadPropertiesFrom(InputStream propertiesInputStream) {
        java.util.Properties properties = new java.util.Properties();

        try {
            properties.load(propertiesInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.load(properties);
    }

    private void load(java.util.Properties properties) {
        log.debug("load properties"); 
        
        setMongoDatabasePort(properties.getProperty("mongo_Port"));
    }
}

