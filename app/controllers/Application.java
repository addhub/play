package controllers;

import aws.S3Service;
import model.User;
import play.Logger;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.UserService;
import views.html.index;

import java.io.File;

public class Application extends Controller {
    UserService userService = new UserService();
    S3Service s3Service = new S3Service();



    public Result viewIndex() {
        User user=userService.getUser(session().get("email"));
        return ok(index.render(user));
    }

    /*public Result uploadAdImg() {
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
    }*/
    public F.Promise<Result> uploadAdImg() {
        final Http.MultipartFormData.FilePart meta = request().body().asMultipartFormData().getFile("picture");
        Logger.info("start upload " + meta.getFilename());
        if (meta != null) {
            File file = meta.getFile();

            return s3Service.uploadAdImg(file).map((uploadResult) -> {
                Logger.info("finished " + meta.getFilename());
                return ok(uploadResult);
            });
        }
        else{
            return F.Promise.pure(badRequest());
        }

    }



}
