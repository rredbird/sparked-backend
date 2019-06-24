package coda.shared.properties;

import org.springframework.beans.factory.annotation.Autowired;
import coda.shared.logging.ILogging;

public class Properties {
    public static final String CorsOriginAdress = "http://localhost:4200";
    
    private int mongoDatabasePort = 27017;
    private String mongoDatabaseIP = "127.0.0.1";
    private String mongoDatabaseName = "coda_db";
    private String mongoCollectionName = "orders";
    private int mongoConnectionTimeout = 1000;
    private int mongoServerSelectionTimeout = 1000;
    private int mongoSocketTimeout = 0;
    private int orderStatusIntervall = 60;
    private String codaIp =  "http://10.0.2.55:5000/";
    private Boolean clearDatabaseOnStartup = false;
    
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
        setMongoCollectionName(properties.getProperty("mongo_Collection_Name"));
        setMongoDatabaseName(properties.getProperty("mongo_Database_Name"));
        setMongoConnectionTimeout(properties.getProperty("mongo_Connection_Timeout"));
        setMongoServerSelectionTimeout(properties.getProperty("mongo_Server_Selection_Timeout"));
        setMongoSocketTimeout(properties.getProperty("mongo_Socket_Timeout"));
        setOrderStatusUpdateIntervall(properties.getProperty("order_Status_Intervall"));
        setCodaIP(properties.getProperty("coda_backend_ip"));
        setClearDatabaseOnStartup(properties.getProperty("clear_database_on_startup"));

        
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

    /// Get and set the intervall in which the listAllEvaluationStatus endpoint is called 
    /// to get status information on all orders.
	public int getOrderStatusUpdateIntervall() {
		return this.orderStatusIntervall;
    }
    public void setOrderStatusUpdateIntervall(String value) {
		this.orderStatusIntervall = Integer.parseInt(value);
	}

	public String getCodaIP() {
		return this.codaIp;
    }
    public void setCodaIP(String value) {
        this.codaIp = value;
    }

	public String getMongoCollectionName() {
		return this.mongoCollectionName;
    }
    public void setMongoCollectionName(String value) {
        this.mongoCollectionName = value;
    }

    public Boolean getClearDatabaseOnStartup() {
		return this.clearDatabaseOnStartup;
    }
    public void setClearDatabaseOnStartup(String value) {
        this.clearDatabaseOnStartup = Boolean.parseBoolean(value);
    }
}

