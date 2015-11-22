package service;

import model.Ad;
import model.Export;
import model.Export.ExportTo;
import org.bson.Document;
import social.SocialExport;
import social.TwitterService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sasinda on 11/21/15.
 * Export ads to other websites, like twitter etc, or export to paper formats etc.
 */
public class ExportService {

    UserService userService=new UserService();
    AdService adService=new AdService();
    Map<ExportTo,SocialExport> socialServices=new HashMap<>();

    public ExportService(){
        //configure
        socialServices.put(ExportTo.TWITTER, new TwitterService());
    }


    public boolean export(Ad adIdnCat, ExportTo to){
        Ad ad = adService.getAd(adIdnCat);

        if(to.val<ExportTo.SOCIAL){
            socialServices.get(to).publish(ad);
        }
        return false;
    }
}
