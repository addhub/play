package model;

import play.data.validation.Constraints;

/**
 * Created by sasinda on 10/15/15.
 */
public class Login {
    @Constraints.Required
    public String email;
    @Constraints.Required
    public String password;

    public String name;
    public String redirect;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
