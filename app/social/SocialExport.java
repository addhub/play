package social;

import model.BaseAd;
import model.User;
import play.mvc.Result;

/**
 * Created by sasinda on 11/21/15.
 */
public interface SocialExport {
   void publish(BaseAd ad, User user);
   Result preview(BaseAd ad);
}
