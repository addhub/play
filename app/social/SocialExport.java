package social;

import model.Ad;
import play.mvc.Result;

/**
 * Created by sasinda on 11/21/15.
 */
public interface SocialExport {
   void publish(Ad ad);
   Result preview(Ad ad);
}
