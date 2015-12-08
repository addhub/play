package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import global.AppConfig;
import model.BaseModel;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import play.libs.Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasinda on 10/14/15.
 */
public class BasicMongoService {

    //settings
    final static String dburi= AppConfig.getString("mongodb.uri");
    final static String dbName= AppConfig.getString("mongodb.name");
    final static MongoClientURI uri = new MongoClientURI(dburi);

    //connection
    final static public MongoClient mongoClient = new MongoClient(uri);
    final static MongoDatabase db = mongoClient.getDatabase(dbName);

    //OM Morphia

    final static Morphia morphia = new Morphia();
    final static Datastore datastore = morphia.createDatastore(mongoClient, dbName);

    static {
        morphia.mapPackage("model");
        datastore.ensureIndexes();
    }

    private final static ObjectMapper mapper =new ObjectMapper();


    public static <T extends BaseModel>  T  as(Class<T> as , Document doc){
        if(doc==null) return null;
        try {
            T t = mapper.readValue(doc.toJson(), as);
            t.setId(doc.get("_id").toString());
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document asDocument(Object o) {
        DBObject adv = (DBObject) JSON.parse(Json.toJson(o).toString());
        return new Document(adv.toMap());
    }

    public List<Document> getList( FindIterable<Document> iterable) {
        List<Document> list=new ArrayList<>();
        for (Document document : iterable) {
            document.put("id", document.remove("_id").toString());
            list.add(document);
        }
        return list;
    }

    public <T extends BaseModel>  List<T> getListAs(Class<T> as ,FindIterable<Document> iterable) {
        List<T> list=new ArrayList<>();
        for (Document document : iterable) {
            document.put("id", document.remove("_id").toString());
            list.add(as(as, document));
        }
        return list;
    }
    public <T extends BaseModel>  List<T> getListAs(Class<T> as ,List<Document> docs) {
        List<T> list=new ArrayList<>();
        for (Document document : docs) {
            document.put("id", document.remove("_id").toString());
            list.add(as(as, document));
        }
        return list;
    }
}
