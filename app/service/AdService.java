package service;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import model.BaseAd;
import model.Query;
import model.User;
import model.ad.Ad;
import model.ad.Categories;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sasinda on 9/30/15.
 * Useful reference for 'Mongodb java driver' : http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
 */
public class AdService extends BasicMongoService {

    public List<Document> getCategories(String name) {
        String collection = "category";
        List<Document> categories;
        if (name == null || name.equalsIgnoreCase("all")) {
            categories = findAll(collection);
        } else {
            categories = new ArrayList<>();
//          Document catdoc = db.getCollection(collection).findOne();
//          categories.add(catdoc);
        }
        return categories;
    }

    /**
     * @param ad
     * @return
     */
    public Document saveAdByCat(BaseAd ad) {
        String cat = ad.getCategory(); // ad's collection name, eg "vehicle"
        //String adid = ad.getId(); //get ad's unique mongo _id
        if (cat != null) {
            Document doc = asDocument(ad);
            db.getCollection(cat).insertOne(doc);
            ad.set_id(doc.getObjectId("_id"));
            return doc;
        }
        return null;
    }


    public <A extends BaseAd> A saveAd(A ad, User user) {
        ad.setUser(user);
        ad.setCreatedOn(ZonedDateTime.now());
        Key<A> save = datastore.save(ad);
        ad.set_id((ObjectId) save.getId());
        return ad;
    }


    public Document getAd(String category,String id){
        Document myAd = db.getCollection(category).find(eq("_id", new ObjectId(id))).first();
        return myAd;
    }

    public <V extends BaseAd> V getAd(V ad){
        if(ad.getClass().getSimpleName().equals("BaseAd")){
            Document myAd = db.getCollection(ad.getCategory()).find(eq("_id", new ObjectId(ad.getId()))).first();
            Class<V> c= (Class<V>) ad.getClass();
            return as(c, myAd);
        }
        return datastore.get(ad);
    }


    public Long deleteAd(String category, String id) {
        DeleteResult id1 = db.getCollection(category).deleteOne(eq("_id", new ObjectId(id)));//http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
        return id1.getDeletedCount();

    }

    public Document updateAd(BaseAd ad) {
        String cat = ad.getCategory(); // ad's collection name, eg "vehicle"
        String adid = ad.getId(); //get ad's unique mongo _id

        if (cat != null && adid != null) {
            Document doc = asDocument(ad);
            db.getCollection(cat).updateOne(eq("_id", adid), new Document("$set", doc)) ;
            return doc;
        }
        return null;
    }




    private List<Document> findAll(String collection) {
        List<Document> docs = new ArrayList<>();
        MongoCursor<Document> cursor = db.getCollection(collection).find().iterator();
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
        String cat = queryMap.get(BaseAd.CATEGORY)[0];
        Query query = new Query(queryMap);
        FindIterable<Document> documents = db.getCollection(cat).find(query.getFilter()).skip(query.getStart()).limit(query.getLimit());
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
