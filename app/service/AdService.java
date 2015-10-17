package service;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import model.Ad;
import model.Query;
import org.apache.commons.lang3.text.WordUtils;
import org.bson.Document;
import org.jongo.Jongo;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasinda on 9/30/15.
 */
public class AdService extends BasicMongoService {

    public List<Document> getCategories(String name) {
        String collection = "category";
        List<Document> categories;
        if (name == null || name.equalsIgnoreCase("all")) {
            categories = findAll(collection);
        } else {
            categories = new ArrayList<>();
//          Document catdoc = addhub.getCollection(collection).findOne();
//          categories.add(catdoc);
        }
        return categories;
    }

    /**
     * @param ad
     * @return
     */
    public Document postAd(Ad ad) {
        String cat = ad.getCategory();
        if (cat != null) {
            Document doc = asDocument(ad);
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

    public List<Document> queryAds(Map<String, String[]> queryMap) {
        String cat = queryMap.get(Ad.CATEGORY)[0];
        Query query = new Query(queryMap);
        FindIterable<Document> documents = addhub.getCollection(cat).find(query.getFilter()).skip(query.getStart()).limit(query.getLimit());
        return getList(documents);
    }

    public List<Document> getList( FindIterable<Document> iterable) {
        List<Document> list=new ArrayList<>();
        for (Document document : iterable) {
            document.put("id", document.remove("_id").toString());
            list.add(document);
        }
        return list;
    }

}
