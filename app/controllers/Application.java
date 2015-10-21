package controllers;

import model.Login;
import model.User;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import service.UserService;
import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    UserService userService=new UserService();

    public Result login() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        String email = loginForm.get().email;
        String pass = loginForm.get().password;

        User user = userService.authenticate(email, pass);
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
        return redirect(
            routes.Application.viewIndex()
        );
    }

    public Result viewIndex() {
        User user=userService.getUser(session().get("email"));
        return ok(index.render(user));
    }




}
