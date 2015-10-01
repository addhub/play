package controllers;

import com.mongodb.async.client.FindIterable;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.Dao;
import views.html.postad;

/**
 * Created by sasinda on 9/30/15.
 */
public class API extends Controller{

    MongoClient mongoClient = MongoClients.create("mongodb://localhost");
    MongoDatabase addhub = mongoClient.getDatabase("addhub");


    public Result postAd(){
       return null;
    }

    /**
     * get category by name
     * @param name  $name or 'all' for all cats
     * @return
     */
    public Result getCategory(String name){
        if(name.equalsIgnoreCase("all")){
            FindIterable<Document> categories = addhub.getCollection("category").find();
            return ok(Json.toJson(categories));
        }else {

        }
        return null;
    }


}
