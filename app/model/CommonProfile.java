package model;

import com.fasterxml.jackson.annotation.*;
import org.pac4j.core.profile.Gender;

import java.util.Map;

/**
 * Created by sasinda on 10/28/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class CommonProfile extends org.pac4j.core.profile.CommonProfile {

    @JsonAnyGetter
    public Map<String, Object> getAttributes() {
        return super.getAttributes();
    }

    @JsonAnySetter
    public void addAttribute(String key, Object value) {
        super.addAttribute(key, value);
    }

    public String getAccessToken() {
        return getAttribute("access_token", String.class);
    }

    public String getAccessSecret() {
        return getAttribute("access_secret", String.class);
    }

    @JsonIgnore
    @Override
    public Gender getGender() {
        return super.getGender();
    }
}
