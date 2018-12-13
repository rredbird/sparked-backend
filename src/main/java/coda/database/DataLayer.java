package coda.database;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.UUID;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoClient;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;


import org.bson.Document;
import org.springframework.stereotype.Component;

import coda.shared.dto.Greeting;
import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;
import coda.order.Order;

@Component("dataLayer")
public class DataLayer {
    private int greetingCounter = 0;
    private MongoClient mongoClient;
    private MongoDatabase db;

    @Autowired
    private Properties properties;

    @Autowired 
    private ILogging log;

    public DataLayer() {
        System.out.println("DataLayer constructor");
    }

    public void initialize() {
        log.debug("initialize datalayer");

        mongoClient = new MongoClient(properties.getMongoDatabaseIP(), properties.getMongoDatabasePort());

        db = mongoClient.getDatabase(properties.getMongoDatabaseName());

        writeTestDocument();
    }
    private void writeTestDocument() {
        MongoCollection<Document> collection = db.getCollection("test");

        Document doc = new Document("name", "MongoDB")
                .append("type", "testentry...")
                .append("timestamp", (new Date()).toString());

        collection.insertOne(doc);

    }

    public List<Order> getOrders() {
        MongoCollection<Document> collection = db.getCollection("orders");

        List<Order> orders = new LinkedList<Order>();

        Block<Document> addToList = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                String data = document.toJson();

                log.debug(data);

                Order order = null;
                ObjectMapper mapper = new ObjectMapper();

                try {
                    order = mapper.readValue(data, Order.class);
                } catch (JsonGenerationException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                     e.printStackTrace();       
                } catch (IOException e) {
                    e.printStackTrace();
                }
        
                orders.add(order);        
            }
        };

        collection.find().forEach(addToList);

        return orders;        
    }

    public Order getOrder(UUID id) {
        MongoCollection<Document> collection = db.getCollection("orders");

        log.debug("Datalayer get order " + id);

        String data = collection.find(eq("id", id.toString())).first().toJson();

        log.debug(data);

        Order order = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            order = mapper.readValue(data, Order.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
             e.printStackTrace();       
        } catch (IOException e) {
            e.printStackTrace();
        }

        return order;
    }
    
    public void saveOrder(Order order) {
        log.debug("Save order " + order.getId());

        MongoCollection<Document> collection = db.getCollection("orders");

        Document doc = new Document("id", order.getId().toString());

        collection.insertOne(doc);

        log.debug("Order " + order.getId() + " saved.");
    }
    
    public Greeting readGreeting(long id) {
        log.debug("read greeting with id: " + id); 
        ++greetingCounter;
        return new Greeting("Hello Coda, this is greeting number " + greetingCounter, id);
    }
    public void writeGreeting(Greeting greeting) {
//        log.debug("write greeting with id: " + greeting.getId());
    }
}
