package controllers;


import exception.RESTException;
import model.Ad;
import model.User;
import org.bson.Document;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.AdService;
import service.ExportService;
import service.UserService;

import java.util.Map;

import static model.Export.*;
import static play.data.Form.form;

/**
 * Created by sasinda on 9/30/15.
 */
public class API extends Controller {


    private AdService adService = new AdService();
    private UserService userService = new UserService();
    private ExportService exportService=new ExportService();

    /**
     * get category by name
     *
     * @param name $name or 'all' for all cats
     * @return
     */
    public Result getCategory(String name) {
        return ok(Json.toJson(adService.getCategories(name)));
    }


    /**
     * Post an advertisement
     * @return
     */
    public Result postAd() {
        Ad ad = validateForModel(Ad.class);
        Document doc = adService.postAd(ad);
        return getDocID(doc, "category must be provided");
    }


    public Result getAd(String category, String id) { //fetch Ad once the Ad is posted by using postAd()
        Document doc = adService.getAd(category, id);
        return getDocID(doc, "The required Advertisement doens not exist");
    }

    public Result queryAds() {
        Map<String, String[]> query = request().queryString();
        return ok(Json.toJson(adService.queryAds(query)));
    }

    public Result deleteAd(String category, String id) {
        long deletes = adService.deleteAd(category, id);
        return ok(Json.toJson(deletes));
    }

    public Result updateAd() {
        Ad ad = validateForModel(Ad.class);
        Document doc = adService.updateAd(ad);
        return getDocID(doc, "The required Advertisement cannot be updated");
    }


    public Result exportAd(){
        Ad ad=extractModel(Ad.class);
        ExportTo to= ExportTo.valueOf(request().getQueryString("to").toUpperCase());
        boolean status=exportService.export(ad,to);
        return ok(""+status);
    }

    public Result createUser() {
        User user=validateForModel(User.class);
        return getDocID(userService.createUser(user), "error creating user");
    }

    public Result getUser(String email) {
        User userEmail = userService.getUser(email);
        return ok(Json.toJson(userEmail));
    }




    private Result getDocID(Document doc, String errorMsg) {
        if (doc != null) {
            return ok(Json.newObject().put("id", doc.get("_id").toString()));
        } else {
            return badRequest(errorMsg);
        }
    }

    private static <T> T validateForModel(Class<T> modelType){
        T model=null;
        try{
            model = form(modelType).bindFromRequest().get();
        }catch (IllegalStateException ex){
            throw new RESTException(ex.getMessage());
        }
        return model;
    }


    private static <T> T extractModel(Class<T> modelType){
        T model=null;
        try{
            model = Json.fromJson(request().body().asJson(), modelType);
        }catch (IllegalStateException ex){
            throw new RESTException(ex.getMessage());
        }
        return model;
    }

}
