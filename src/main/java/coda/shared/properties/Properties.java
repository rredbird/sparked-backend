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

import coda.shared.logging.ILogging;

@Component("properties")
public class Properties {
    private int mongoDatabasePort = 27017;

    @Autowired
    private ILogging log;

    public int  getMongoDatabasePort() {
        log.debug("get mongo port: " + mongoDatabasePort);
        return mongoDatabasePort; 
    }

    public void setMongoDatabasePort(int port) { 
        mongoDatabasePort = port; 
    }

    public void setMongoDatabasePort(String port) {
        log.debug("set database port");
        log.debug(port.toString());
        setMongoDatabasePort(Integer.parseInt(port));
    }

    public void load(java.util.Properties properties) {
        log.debug("load properties"); 
        setMongoDatabasePort(properties.getProperty("mongo_Port"));
    }
}

