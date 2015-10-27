package service;

import exception.AuthException;
import model.Login;
import model.User;
import org.bson.Document;
import play.api.libs.json.Json;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sasinda on 10/15/15.
 */
public class UserService extends BasicMongoService {



    public Document createUser(User u) {
        Document doc = asDocument(u);
        addhub.getCollection("user").insertOne(doc);
        return doc;
    }

    public User getUser(String email) {
        return as(User.class,addhub.getCollection("user").find(eq("email", email)).first());
    }
}
