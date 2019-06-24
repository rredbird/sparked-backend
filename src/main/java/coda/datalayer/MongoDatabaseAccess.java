package coda.datalayer;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;

import org.bson.Document;
import org.bson.conversions.Bson;

import coda.shared.properties.Properties;
import coda.shared.logging.ILogging;


public class MongoDatabaseAccess {
    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> ordersCollection;

    @Autowired
    private Properties properties;

    @Autowired
    private ILogging log;

    public MongoDatabaseAccess() { 
    }

    public void initialize() {
        log.debug("initialize datalayer");

        MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();

        optionsBuilder.connectTimeout(properties.getMongoConnectionTimeout());
        optionsBuilder.socketTimeout(properties.getMongoSocketTimeout());
        optionsBuilder.serverSelectionTimeout(properties.getMongoServerSelectionTimeout());
        
        optionsBuilder.build();
        
        mongoClient = new MongoClient(properties.getMongoDatabaseIP(), properties.getMongoDatabasePort());

        db = mongoClient.getDatabase(properties.getMongoDatabaseName());
        ordersCollection = db.getCollection(properties.getMongoCollectionName());

        if(properties.getClearDatabaseOnStartup()) {
            ordersCollection.drop();
            
            ordersCollection = db.getCollection(properties.getMongoCollectionName());
        }
    }

    public MongoCollection<Document> getOrdersCollection() { return ordersCollection; }

    public void invokeForAll(MongoCollection<Document> collection, IFoundItem function) {
        invokeForFiltered(collection.find(), function);
    }

    public void invokeForFiltered(FindIterable<Document> filteredCollection, IFoundItem function) {
        Block<Document> foundDocuments = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                function.process(document.toJson());
            }
        };
        filteredCollection.forEach(foundDocuments);
    }

    public String getFirst(MongoCollection<Document> ordersCollection2, Bson expression) {
        Document orderDocument = ordersCollection.find(expression).first();
        
        if(orderDocument == null) {
            log.debug("No document found to: " + expression.toString());
            return null;
        }    
        
        return orderDocument.toJson();
	}

    @Deprecated
	public void insertJson(MongoCollection<Document> ordersCollection, String json) {
        ordersCollection.insertOne(Document.parse(json));
    }
    
    public void upsert(MongoCollection<Document> ordersCollection, String id, String json) {
        Bson filter = Filters.eq("_id", id);

        Document update = Document.parse(json);
        ReplaceOptions options = new ReplaceOptions().upsert(true);

        ordersCollection.replaceOne(filter, update, options);
        // ordersCollection.updateOne(filter, update, options);
    }
}
