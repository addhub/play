package controllers;



import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import model.Ad;
import org.bson.Document;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.Mongo;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasinda on 9/30/15.
 */
public class API extends Controller{


    Mongo mongo=new Mongo();

    /**
     * get category by name
     * @param name  $name or 'all' for all cats
     * @return
     */
    public Result getCategory(String name){
        return ok(Json.toJson(mongo.getCategory(name)));
    }




    /**
     * Post an advertisement.
     * @return
     */
    public Result postAd(){
        Ad ad=Json.fromJson(request().body().asJson(), Ad.class);

        Document doc=mongo.postAd(ad);
        if(doc!=null){
            return ok(Json.newObject().put("id",doc.get("_id").toString()));
        }else {
            return badRequest("category must be provided");
        }
    }





}
