package global;

import exception.AuthException;
import exception.RESTException;
import jobs.JobScheduler;
import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;
import play.GlobalSettings;
import service.BasicMongoService;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.unauthorized;

/**
 * Created by sasinda on 10/16/15.
 */
public class Global extends GlobalSettings {

    public Promise<Result> onError(RequestHeader request, Throwable t) {
        if(t instanceof AuthException){
            return Promise.<Result>pure(badRequest(t.getMessage()));
        }else if(t instanceof RESTException){
            return Promise.<Result>pure(badRequest(t.getMessage()));
        }
        return super.onError(request,t);
//        return Promise.<Result>pure(badRequest(t.toString()));
    }

    public Promise<Result> onBadRequest(RequestHeader request, String error) {
        return Promise.<Result>pure(badRequest("Don't try to hack the URI!"));
    }

    @Override
    public void onStop(Application app) {
        BasicMongoService.mongoClient.close();
    }

    @Override
    public void onStart(Application app) {
        super.onStart(app);
        JobScheduler.init();
    }
}