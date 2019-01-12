package coda.database;

import java.util.List;
import java.util.LinkedList;
import java.util.UUID;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoClient;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


import org.bson.Document;

import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;
import coda.order.Order;

public class DataLayer {
    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> ordersCollection;

    @Autowired
    private Properties properties;

    @Autowired
    private ILogging log;

    public DataLayer() { }

    public void initialize() {
        log.debug("initialize datalayer");

        mongoClient = new MongoClient(properties.getMongoDatabaseIP(), properties.getMongoDatabasePort());

        db = mongoClient.getDatabase(properties.getMongoDatabaseName());
        ordersCollection = db.getCollection("orders");
    }

    public List<Order> getOrders() {
        List<Order> orders = new LinkedList<Order>();

        Block<Document> addToList = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                orders.add(orderFromJson(document.toJson()));        
            }
        };

        ordersCollection.find().forEach(addToList);

        return orders;        
    }

    public Order getOrder(UUID id) {
        Document orderDocument = ordersCollection.find(eq("_id", id.toString())).first();
        
        if(orderDocument == null) {
            log.debug("Order not found: " + id.toString());
            return null;
        }

        String data = orderDocument.toJson();
        log.debug("Loading Order: " + data);
        return orderFromJson(data);
    }
    
    public void saveOrder(Order order) {
        String data = jsonFromOrder(order);
        ordersCollection.insertOne(Document.parse(data));

        log.debug("Saving Order: " + data);
    }
    
    public void deleteOrder(Order order) {
        log.debug("Deleting Order: " + jsonFromOrder(order));
        ordersCollection.deleteOne(eq("_id", order.getId().toString()));
    }

    private Order orderFromJson(String orderAsJsonString) {
        Order order = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            order = mapper.readValue(orderAsJsonString, Order.class);
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);       
        } catch (IOException e) {
            log.exception(e);
        }

        return order;
    }

    private String jsonFromOrder(Order order) {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = "";
        try {
            orderJson = objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException jsonProcessingException) {
            log.exception(jsonProcessingException);
        }

        return orderJson;
    }
}
