package model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by sasinda on 10/17/15.
 */
public class BaseModel {

    @Id
    private ObjectId _id;

    public String getId() {
        return _id.toString();
    }

    public void setId(String id) {
        this._id=new ObjectId(id);
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
