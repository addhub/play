package controllers;


import exception.AuthException;
import exception.RESTException;
import model.BaseAd;
import model.BaseModel;
import model.User;
import model.ad.Categories;
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
    public Result postAd(String category) {
        BaseAd modelType = Categories.getModelType(Categories.Category.valueOf(category));
        BaseAd ad = validateForModel(modelType.getClass());
        User user=getCurrentUser();
        BaseAd baseAd = adService.saveAd(ad, user);
        adService.savePictures(ad, user);
        return getModelId(baseAd,"Error saving, category must be provided");
    }


    public Result getAd(String category, String id) { //fetch Ad once the Ad is posted by using postAd()
        BaseAd search=new BaseAd(category,id);
        return ok(Json.toJson(adService.getAd(search)));
    }

    //Get ads from all categories
    public Result queryAds() {
        Map<String, String[]> query = request().queryString();
        return ok(Json.toJson(adService.queryAds(query)));
    }

    //get ads from given category
    public Result queryAdsBy(String category) {
        Map<String, String[]> query = request().queryString();
        return ok(Json.toJson(adService.queryAds(query)));
    }

    public Result deleteAd(String category, String id) {
        long deletes = adService.deleteAd(category, id);
        return ok(Json.toJson(deletes));
    }

    public Result updateAd(String category) {
        BaseAd ad = validateForModel(BaseAd.class);
        Document doc = adService.updateAd(ad);
        return getDocID(doc, "The required Advertisement cannot be updated");
    }


    public Result exportAd(String category, String id){
        BaseAd ad=extractModel(BaseAd.class);
        ExportTo to= ExportTo.valueOf(request().getQueryString("to").toUpperCase());
        String username=session().get("username");
        //TODO: use aop to check authentication
        if(username!=null){
            boolean status=exportService.export(username, ad,to);
            return ok(""+status);
        }else {
            return unauthorized("{\"success\":false, \"message\":\"user not logged in\"}");
        }
    }

    public Result createUser() {
        User user=validateForModel(User.class);
        return getDocID(userService.createUser(user), "error creating user");
    }

    public Result getUser(String email) {
        User userEmail = userService.getUser(email);
        return ok(Json.toJson(userEmail));
    }



    private Result getModelId(BaseModel model, String errorMsg) {
        if (model != null) {
            return ok(Json.newObject().put("id", model.getId()));
        } else {
            return badRequest(errorMsg);
        }
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

    private User getCurrentUser(){
        String username = session().get("username");
        if(username==null){
            throw new AuthException("User not logged in");
        }
        return userService.getUser(username);
    }

}
