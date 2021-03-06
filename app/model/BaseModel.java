package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;


public class BaseModel {

    @Id
    @JsonIgnore
    private ObjectId _id;

    public String getId() {
        if(_id!=null){
            return _id.toString();
        }
        return null;
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
