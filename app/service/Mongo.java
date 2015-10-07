package service;


import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import model.Ad;
import org.bson.Document;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasinda on 9/30/15.
 */
public class Mongo {
    MongoClient mongoClient = new MongoClient("localhost");
    MongoDatabase addhub = mongoClient.getDatabase("addhub");

    public List<Document> getCategory(String name) {
        String collection = "category";
        List<Document> categories;
        if (name == null || name.equalsIgnoreCase("all")) {
            categories = findAll(collection);
        } else {
            categories = new ArrayList<>();
//            Document catdoc = addhub.getCollection(collection).findOne();
//            categories.add(catdoc);
        }
        return categories;
    }

    public Document postAd(Ad ad) {
        String cat=ad.getCategory();
        cat.replace(' ','_');
        if(cat!=null){
            DBObject adv= (DBObject) JSON.parse(Json.toJson(ad).toString());
            Document doc=new Document(adv.toMap());
            addhub.getCollection(cat).insertOne(doc);
            return doc;
        }
        return null;
    }


    private List<Document> findAll(String collection) {
        List<Document> docs = new ArrayList<>();
        MongoCursor<Document> cursor = addhub.getCollection(collection).find().iterator();
        try {
            while (cursor.hasNext()) {
                docs.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return docs;
    }

}
