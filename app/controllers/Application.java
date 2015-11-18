package controllers;

import model.Login;
import model.User;
import org.apache.commons.io.FileUtils;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import service.UserService;
import views.html.*;

import java.io.File;
import java.io.IOException;

import static play.data.Form.form;

public class Application extends Controller {

    UserService userService=new UserService();

    public Result viewIndex() {
        User user=userService.getUser(session().get("email"));
        return ok(index.render(user));
    }

    public Result uploadAdImg() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("file");
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            String username=session().get("username");
            try {
                FileUtils.moveFile(file, new File("/Users/Shared/upload/"+username, fileName));
            } catch (IOException e) {
                e.printStackTrace();
                return badRequest("file already uploaded");
            }
            return ok("File uploaded");
        } else {
            return badRequest();
        }
    }


}
