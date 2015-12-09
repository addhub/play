package controllers;

import ext.aws.S3Service;
import model.User;
import org.apache.commons.io.FileUtils;
import play.Logger;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.UserService;
import views.html.index;

import java.io.File;
import java.io.IOException;

public class Application extends Controller {
    UserService userService = new UserService();
    S3Service s3Service = new S3Service();

    public static final String PICTURE_FOLDER ="/Users/Shared/upload/";


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
                FileUtils.moveFile(file, new File(PICTURE_FOLDER + username, fileName));
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
