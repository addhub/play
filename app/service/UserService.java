package service;

import com.mongodb.client.result.UpdateResult;
import model.Ad;
import model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sasinda on 10/15/15.
 */
public class UserService extends BasicMongoService {



    public Document createUser(User u) {
        Document doc = asDocument(u);
        addhub.getCollection("User").insertOne(doc);
        return doc;
    }

    public long updateUser(User u) {
        Document doc = asDocument(u);
        UpdateResult result = addhub.getCollection("User").updateOne(eq("_id", new ObjectId(u.getId())), new Document("$set", doc));
        return result.getModifiedCount();
    }

    public User getUser(String username) {
        return as(User.class,addhub.getCollection("User").find(eq("username", username)).first());
    }

}
