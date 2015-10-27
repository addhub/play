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

    public Result viewIndex() {
        User user=userService.getUser(session().get("email"));
        return ok(index.render(user));
    }




}
