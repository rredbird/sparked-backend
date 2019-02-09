package coda.shared.properties;

import org.springframework.beans.factory.annotation.Autowired;
import coda.shared.logging.ILogging;

public class Properties {
    public static final String CorsOriginAdress = "http://localhost:4200";
    
    private int mongoDatabasePort = 27017;
    private String mongoDatabaseIP = "127.0.0.1";
    private String mongoDatabaseName = "coda_db";
    
    @Autowired
    private ILogging log;



    // --- Port ---
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


    // --- IP ---
    public String getMongoDatabaseIP() {
        log.debug("get mongo ip: " + mongoDatabaseIP);
        return mongoDatabaseIP;
    }

    public void setMongoDatabaseIP(String ip) {
        mongoDatabaseIP = ip;
    }


    // --- Name ---
    public String getMongoDatabaseName() {
        log.debug("get mongo database name: " + mongoDatabaseName);
        return mongoDatabaseName;
    }

    public void setMongoDatabaseName(String name) {
        mongoDatabaseName = name;
    }



    public void load(java.util.Properties properties) {
        log.debug("load properties"); 
        setMongoDatabasePort(properties.getProperty("mongo_Port"));
        setMongoDatabaseIP(properties.getProperty("mongo_IP"));
        setMongoDatabaseName(properties.getProperty("mongo_Name"));
    }
}

