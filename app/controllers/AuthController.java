package controllers;

import com.google.inject.Inject;
import exception.AuthException;
import jdk.nashorn.internal.runtime.URIUtils;
import model.Login;
import model.User;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.play.ApplicationLogoutController;
import org.pac4j.play.CallbackController;
import org.pac4j.play.java.UserProfileController;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import play.utils.UriEncoding;
import service.UserService;

import java.net.URI;

import static play.data.Form.form;

/**
 * Created by sasinda on 10/26/15.
 */
public class AuthController extends UserProfileController<CommonProfile> {

    @Inject
    UserService userService;
    @Inject
    CallbackController callbackController;
    @Inject
    ApplicationLogoutController logoutController;
    @Inject
    API api;

    public Result signup() {
        api.createUser();
        return login();
    }

    public Result login() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        String username = loginForm.get().username;
        String pass = loginForm.get().password;
        //TODO use MongoAuthenticator
        User user = authenticate(username, pass);
        //if success
        return afterLoginSuccess(user);
    }

    private Result afterLoginSuccess(User user) {
        session().clear();
        session("username", user.getUsername());
        session("name", user.getDisplayName());
        response().setCookie("username", user.getUsername());
        response().setCookie("displayName", user.getDisplayName().split(" ")[0]);
        if(user.getPictureUrl()!=null){
            response().setCookie("pictureUrl", UriEncoding.decodePath(user.getPictureUrl(),"UTF-8"));
        }
        user.setPassword(null);
        return ok(Json.toJson(user));
    }

    public Result logout() {
        session().clear();
        response().discardCookie("username");
        response().discardCookie("displayName");
        response().discardCookie("pictureUrl");
        response().discardCookie("socialLogin");
        logoutController.logout();
        return redirect("/");
    }

    /**
     * return error code if fail
     *
     * @param username
     * @param password
     * @return
     */
    private User authenticate(String username, String password) {
        User user = userService.getUser(username);
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
        if (userProfile != null) {
            if (session().get("username") != null) {
                //an already logged in user adding or refreshing a social profile token
                //TODO
                return redirect("/#/myAccount/profile/idTODO");
            } else {
                //loging in through social login
                return socialLogin(userProfile);
            }
        }
        return result;
    }

    private Result socialLogin(CommonProfile userProfile) {
        //first time --> signup
        User user = new User(userProfile.getId(), "autogen");
        user.setDisplayName(userProfile.getDisplayName());
        user.setPictureUrl(userProfile.getPictureUrl());
        String provider=request().getQueryString("client_name");
        provider=provider.replace("Client","").toLowerCase();
        user.setProfile(provider,userProfile);
        response().setCookie("socialLogin", provider);

        User existUser = userService.getUser(user.getUsername());
        if (existUser != null) {
            //login
            userService.updateUser(user);
            afterLoginSuccess(user);
            return redirect("/#/login");
        } else {
            //signup
            userService.createUser(user);
            afterLoginSuccess(user);
            return redirect("/#/login");
        }
    }
}
