package model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Map;

/**
 * Created by sasinda on 10/28/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Ignore
public class CommonProfile extends org.pac4j.core.profile.CommonProfile {

    @JsonAnyGetter
    public Map<String, Object> getAttributes() {
        return super.getAttributes();
    }


    @JsonAnySetter
    public void addAttribute(String key, Object value) {
        super.addAttribute(key, value);
    }
}
