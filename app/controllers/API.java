package controllers;



import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import model.Ad;
import org.bson.Document;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.Dao;
import views.html.postad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasinda on 9/30/15.
 */
public class API extends Controller{

    MongoClient mongoClient = new MongoClient("localhost");
    MongoDatabase addhub = mongoClient.getDatabase("addhub");



    /**
     * get category by name
     * @param name  $name or 'all' for all cats
     * @return
     */
    public Result getCategory(String name){
        String collection="category";
        if(name.equalsIgnoreCase("all")){
            List< Document> categories = findAll(collection);
           return ok(Json.toJson(categories));
        }else {

        }
        return null;
    }




    /**
     * get category by name
     * @return
     */
    public Result postAd(){
        Ad ad=Json.fromJson(request().body().asJson(), Ad.class);
        String cat=ad.getCategory();
        if(cat!=null){
            DBObject adv= (DBObject) JSON.parse(Json.toJson(ad).toString());
            addhub.getCollection(cat);
        }

        return ok("");
    }



    private List<Document> findAll(String collection) {
        List<Document> docs =new ArrayList<>();
        MongoCursor<Document> cursor=addhub.getCollection(collection).find().iterator();
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
