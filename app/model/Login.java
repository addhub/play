package model;

import play.data.validation.Constraints;

/**
 * Created by sasinda on 10/15/15.
 */
public class Login {
    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;

    private String redirect;


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

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
