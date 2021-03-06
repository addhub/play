package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import play.data.validation.Constraints;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sasinda on 10/15/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel {

    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;

    private String email;
    private String mobile; //workphone during day time
    private String displayName;
    private String pictureUrl;
    private String address;
    private String location; //actully user's city
    private Long lastUpdated;
    private String otherInfo;
    //Notifications
    private boolean notifications;
    private long lastNotificationAt;
    private String notificationQuery;

    private String skypeId;

    public CommonProfile main;
    public Map<ProfileKey, CommonProfile> profiles = new HashMap<>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String usercity) {
        this.address = usercity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public CommonProfile getProfile(ProfileKey key){
        return profiles.get(key);
    }
    public void setProfile(String provider, CommonProfile profile ){
        String p=provider.toUpperCase();
        setProfile(ProfileKey.keyOf(p), profile);
    }
    public void setProfile(ProfileKey key, CommonProfile profile){
        profiles.put(key, profile);
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public Long getLastNotificationAt() {
        return lastNotificationAt;
    }

    public void setLastNotificationAt(Long lastNotificationAt) {
        this.lastNotificationAt = lastNotificationAt;
    }

    public String getNotificationQuery() {
        return notificationQuery;
    }

    public void setNotificationQuery(String notificationQuery) {
        this.notificationQuery = notificationQuery;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String theInfo) {
        if (theInfo.length()>0){
            this.otherInfo = theInfo;
        }
        else{
            this.otherInfo = "S/he is very lazy, hence nothing left here!";
        }


    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }


    public enum ProfileKey {
        TWITTER;

        public static ProfileKey keyOf(String provider) {
            ProfileKey key=null;
            switch (provider){
                case "TWITTER": key=TWITTER;
            }
            return key;
        }
    }
}
