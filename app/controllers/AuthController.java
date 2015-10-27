package controllers;

import com.google.inject.Inject;
import exception.AuthException;
import model.Login;
import model.User;
import org.bson.Document;
import org.pac4j.core.client.Clients;
import org.pac4j.core.client.IndirectClient;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.play.ApplicationLogoutController;
import org.pac4j.play.CallbackController;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.java.UserProfileController;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import service.UserService;

import static com.mongodb.client.model.Filters.eq;
import static play.data.Form.form;

/**
 * Created by sasinda on 10/26/15.
 */
public class AuthController extends UserProfileController<CommonProfile>{

    @Inject
    UserService userService;
    @Inject
    CallbackController callbackController;
    @Inject
    ApplicationLogoutController logoutController;
    @Inject
    API api;

    public Result signup(){
        api.createUser();
        return login();
    }

    public Result login() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        String email = loginForm.get().email;
        String pass = loginForm.get().password;

        User user = authenticate(email, pass);
        //if success
        session().clear();
        session("email", email);
        session("name", user.getName());
        response().setCookie("user-name", user.getName());
        user.setPassword(null);
        return ok(Json.toJson(user));
    }

    public Result logout() {
        session().clear();
        response().discardCookie("user-name");
        logoutController.logout();
        return redirect("/");
    }

    /**
     * return error code if fail
     *
     * @param email
     * @param password
     * @return
     */
    private User authenticate(String email, String password) {
        User user = userService.getUser(email);
        if (user == null) {
            throw new AuthException("user not found");
        } else if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new AuthException("password incorrect");
        }
    }


    public Result callback() {
        Result result = callbackController.callback();
        CommonProfile userProfile = getUserProfile();
        if(userProfile !=null){

        }
        return result;
    }
}
