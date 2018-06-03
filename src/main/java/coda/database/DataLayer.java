package coda.database;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoClient;
import com.mongodb.annotations.Beta;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import coda.shared.dto.Greeting;
import coda.shared.properties.Properties;
import coda.shared.logging.Logging;

@Component("dataLayer")
public class DataLayer {
    private int greetingCounter = 0;
    private MongoClient mongoClient;
    private MongoDatabase db;

    @Autowired
    private Properties properties;

    @Autowired 
    private Logging log;

    public DataLayer() {
        System.out.println("DataLayer constructor");
    }

    public void initialize() {
        log.debug("initialize datalayer");

        mongoClient = new MongoClient("localhost", properties.getMongoDatabasePort());

        db = mongoClient.getDatabase("test");

        writeTestDocument();
    }
    private void writeTestDocument() {
        MongoCollection<Document> collection = db.getCollection("test");

        Document doc = new Document("name", "MongoDB")
                .append("type", "testentry...")
                .append("timestamp", (new Date()).toString());

        collection.insertOne(doc);

    }
    
    public Greeting readGreeting(long id) {
        log.debug("read greeting with id: " + id); 
        ++greetingCounter;
        return new Greeting(id, "Hello Coda, this is greeting number " + greetingCounter);
    }
    public void writeGreeting(Greeting greeting) {
//        log.debug("write greeting with id: " + greeting.getId());
    }
}
