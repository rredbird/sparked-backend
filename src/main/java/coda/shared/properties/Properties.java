package coda.shared.properties;

import java.util.Arrays;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("properties")
public class Properties {
    private int mongoDatabasePort = 27017;

    public int  getMongoDatabasePort() { return mongoDatabasePort; }
    public void setMongoDatabasePort(int port) { mongoDatabasePort = port; }
    public void setMongoDatabasePort(String port) { mongoDatabasePort = Integer.parseInt(port); }
}

