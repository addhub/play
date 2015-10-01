package service;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;

/**
 * Created by sasinda on 9/30/15.
 */
public class Dao {
    // To directly connect to the default server localhost on port 27017

    MongoClient mongoClient = MongoClients.create("mongodb://localhost");
    MongoDatabase database = mongoClient.getDatabase("addhub");


}
