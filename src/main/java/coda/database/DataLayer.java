package coda.database;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.annotations.Beta;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import coda.shared.dto.Greeting;

@Component("dataLayer")
public class DataLayer {
    private int greetingCounter = 0;
    private MongoClient mongoClient;
    private MongoDatabase db;

    public DataLayer() {
        mongoClient = new MongoClient("localhost", 32768);

        db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("test");

        System.out.println(collection.count());

        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(doc);

        System.out.println(collection.count());
    }

    public Greeting readGreeting(long id) {
        ++greetingCounter;
        return new Greeting(id, "Hello Coda, this is greeting number " + greetingCounter);
    }
    public void writeGreeting(Greeting greeting) {

    }
}