package social;


import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import model.Ad;

import java.io.IOException;
import java.net.URL;

import static java.lang.String.format;

public class FacebookService {

    private final DefaultFacebookClient facebookClient;
    String accessToken = "CAACEdEose0cBAKHPJQ2LuFtt8TmxyS962lExoI4vuVsiHJZAKGztsUI2bwQUjZCZBbsOtDdH1JfuDZCw4MEJndIdytZB4b7pE0n81sUUtJ3ojaYX6TC2KzrhZCEFGGq50g5FZBWhSelHd1bYEVcZBOgGoi1XqruXN068qsHF5muSEwhZCwZBSuOvFNZCKMis05gQtIkSxkQWr3lUksZBD5XITUuQ";
    String MY_APP_SECRET = "771d4e3b4db5a69c76d141d2f7474647";


    public FacebookService() {
        facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_5);
    }

    private String parseAd(Ad ad) { // parse Ad obj to a string since twitter udpate method accept String as parameter
        String adTitle = ad.getTitle().replaceAll("[\\-\\+\\.\\^:,]","");
        String adkey = ad.getKeywords();
        String adinfo = adTitle + adkey;
        if (adinfo.length()>=118) {
            adinfo = adinfo.substring(0,115);
        }
        String shorturl = ad.getAdurl();
        //System.out.println(adinfo+shorturl);
        return adinfo+" "+shorturl;
    }

    public String publishMessage(Ad ad){
        System.out.println("* Feed Publishing");
        FacebookType publishMessageResponse =
                facebookClient.publish(parseAd(ad), FacebookType.class, Parameter.with("message", "RestFB test"));

        System.out.println("Published message ID: " + publishMessageResponse.getId());
        return publishMessageResponse.getId();
    }

    public String publishPhoto(Ad ad) throws IOException {
        System.out.println("* Binary file publishing *");

        FacebookType publishPhotoResponse =
                facebookClient.publish("me/photos", FacebookType.class,
                        BinaryAttachment.with("test.png", getClass().getResourceAsStream(String.valueOf(new URL(ad.getPictureUrls().get(0)).openStream()))),
                        Parameter.with("message", "Test picture"));

        System.out.println("Published photo ID: " + publishPhotoResponse.getId());
        return publishPhotoResponse.getId();
    }

    public void delete(String objectId) {
        System.out.println("* Object deletion *");
        System.out.println(format("Deleted %s: %s", objectId, facebookClient.deleteObject(objectId)));
    }

}
