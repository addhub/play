package exception;

import service.BasicMongoService;

/**
 * Created by sasinda on 10/16/15.
 */
public class AuthException extends RuntimeException {

    public boolean authenticated=false;
    public boolean authorized=false;

    public AuthException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return BasicMongoService.asDocument(this).toJson();
    }
}
