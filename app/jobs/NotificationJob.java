package jobs;

import com.mongodb.client.model.Filters;
import ext.twilio.TwilioService;
import model.BaseAd;
import model.User;
import org.bson.Document;
import service.AdService;
import service.UserService;

import java.util.List;

/**
 * Created by sasinda on 12/7/15.
 */
public class NotificationJob implements Runnable {
    AdService adService=new AdService();
    UserService userService=new UserService();
    TwilioService twilioService=new TwilioService();

    @Override
    public void run() {
        System.out.println("notifications job running");
        List<User> users = userService.queryUsers(Filters.eq("notifications", true));
        for (User user : users) {
            String query=user.getNotificationQuery();
            query=query+"&createdOn[$gt]="+ user.getLastNotificationAt();
            List<Document> documents = adService.queryAds(query);
            List<BaseAd> adsList = adService.getListAs(BaseAd.class, documents);
            if(!adsList.isEmpty()){
                twilioService.sendSMS(user.getMobile(),"New ads for your query");
            }
        }
    }
}
//a={ createdOn: { $gt: 12121212 } }
//$.param(a)
//createdOn%5B%24gt%5D=12121212
//"category=Vehicle&subCat=Cars&price%5B%24gt%5D=10000"
//"category=Vehicle&subCat=Cars&price[$gt]=10000"