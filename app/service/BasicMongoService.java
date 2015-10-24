package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import global.Config;
import model.BaseModel;
import org.bson.Document;
import org.jongo.Jongo;
import play.Play;
import play.libs.Json;


import java.io.IOException;

/**
 * Created by sasinda on 10/14/15.
 */
public class BasicMongoService {

    String dburi= Config.getString("mongodb.uri");
    String dbName=Config.getString("mongodb.name");
    MongoClientURI uri = new MongoClientURI(dburi);

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase addhub = mongoClient.getDatabase(dbName);
    Jongo jaddhub = new Jongo(mongoClient.getDB(dbName));


    private static ObjectMapper mapper =new ObjectMapper();
    public static <T extends BaseModel>  T as(Class<T> as , Document doc){
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
}
