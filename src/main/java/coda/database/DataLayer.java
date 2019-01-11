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
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import static com.mongodb.client.model.Filters.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


import org.bson.Document;

import coda.shared.dto.Greeting;
import coda.shared.dto.OrderDto;
import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;
import coda.order.Order;

public class DataLayer {
    private int greetingCounter = 0;
    private MongoClient mongoClient;
    private MongoDatabase db;

    @Autowired
    private Properties properties;

    @Autowired
    private ILogging log;

    public DataLayer() { }

    public void initialize() {
        log.debug("initialize datalayer");

        mongoClient = new MongoClient(properties.getMongoDatabaseIP(), properties.getMongoDatabasePort());

        db = mongoClient.getDatabase(properties.getMongoDatabaseName());
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
                String orderJson = document.toJson();

                Order order = new Order();
                OrderDto orderDto = null;
                ObjectMapper mapper = new ObjectMapper();

                try {
                    orderDto = mapper.readValue(orderJson, OrderDto.class);
                } catch (JsonGenerationException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                     e.printStackTrace();       
                } catch (IOException e) {
                    e.printStackTrace();
                }
        
                order.loadFromDto(orderDto);
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

        Order order = new Order();
        ObjectMapper mapper = new ObjectMapper();

        try {
            log.trace("Loading order: " + data);
            OrderDto orderDto = mapper.readValue(data, OrderDto.class);
            order.loadFromDto(orderDto);
            
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
        MongoCollection<Document> collection = db.getCollection("orders");

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = "";
        try {
            orderJson = objectMapper.writeValueAsString(order.getDto());
        } catch (JsonProcessingException jsonProcessingException) {
            log.exception(jsonProcessingException);
        }

        Document document = Document.parse(orderJson);
        collection.insertOne(document);

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
