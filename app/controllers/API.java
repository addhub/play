package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import exception.RESTException;
import model.Ad;
import model.User;
import org.bson.Document;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.AdService;
import service.UserService;

import java.util.Map;

import static play.data.Form.form;

/**
 * Created by sasinda on 9/30/15.
 */
public class API extends Controller {


    private AdService adService = new AdService();
    private UserService userService = new UserService();


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
        return getDocIDResponse(doc,"category must be provided");
    }


    public Result getAd(String category, String id) { //fetch Ad once the Ad is posted by using postAd()
        Document doc = adService.getAd(category, id);
        return getDocIDResponse(doc,"The required Advertisement doens not exist");
    }

    public Result queryAds() {
        Map<String, String[]> query = request().queryString();
        return ok(Json.toJson(adService.queryAds(query)));
    }

    public long deleteAd(String category, String id) {
        long doc = adService.deleteAd(category, id);
        return doc;
    }

    public Result updateAd() {
        Ad ad = validateForModel(Ad.class);
        Document doc = adService.updateAd(ad);
        return getDocIDResponse(doc,"The required Advertisement cannot be updated");
    }

    public Result createUser() {
        User user=validateForModel(User.class);
        return getDocIDResponse(userService.createUser(user),"error creating user");
    }

    public Result getUser(String email) {
        User userEmail = userService.getUser(email);
        return ok(Json.toJson(userEmail));
    }

    private Result getDocIDResponse(Document doc, String errorMsg) {
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
}
