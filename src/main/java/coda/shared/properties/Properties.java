package coda.shared.properties;

import org.springframework.beans.factory.annotation.Autowired;
import coda.shared.logging.ILogging;

public class Properties {
    public static final String CorsOriginAdress = "http://localhost:4200";
    
    private int mongoDatabasePort = 27017;
    private String mongoDatabaseIP = "127.0.0.1";
    private String mongoDatabaseName = "coda_db";
    private int mongoConnectionTimeout = 1000;
    private int mongoServerSelectionTimeout = 1000;
    private int mongoSocketTimeout = 0;
    
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
        setMongoConnectionTimeout(properties.getProperty("mongo_Connection_Timeout"));
        setMongoServerSelectionTimeout(properties.getProperty("mongo_Server_Selection_Timeout"));
        setMongoSocketTimeout(properties.getProperty("mongo_Socket_Timeout"));
    }

	public int getMongoConnectionTimeout() { return this.mongoConnectionTimeout; }
	public void setMongoConnectionTimeout(String mongoConnectionTimeout) { 
        this.mongoConnectionTimeout = Integer.parseInt(mongoConnectionTimeout);
    }

	public int getMongoServerSelectionTimeout() { return 0;	}
    public void setMongoServerSelectionTimeout(String mongoServerSelectionTimeout) { 
        Integer.parseInt(mongoServerSelectionTimeout); 
    }

	public int getMongoSocketTimeout() { return 1000; }
	public void setMongoSocketTimeout(String mongoSocketTimeout) { 
        this.mongoSocketTimeout = Integer.parseInt(mongoSocketTimeout); 
    }    
}

