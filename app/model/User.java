package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.pac4j.core.profile.CommonProfile;
import play.data.validation.Constraints;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sasinda on 10/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel {

    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;

    private String email;
    private String displayName;
    private String pictureUrl;

    public CommonProfile main;
    Map<String, CommonProfile> profiles = new HashMap<>();

    public User() {
    }

    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
