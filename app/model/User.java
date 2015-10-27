package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.http.profile.HttpProfile;
import play.data.validation.Constraints;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sasinda on 10/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel{

    @Constraints.Required
    private String name;
    @Constraints.Required
    private String email;
    @Constraints.Required
    private String password;

    public CommonProfile main;
    Map<String, CommonProfile> profiles=new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
