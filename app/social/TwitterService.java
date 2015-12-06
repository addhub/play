package social;

import model.BaseAd;
import model.CommonProfile;
import model.User;
import play.Play;
import play.mvc.Result;
import play.mvc.Results;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jingxiapang on 10/28/15.
 */
public class TwitterService implements SocialExport {

    public ConfigurationBuilder cb = new ConfigurationBuilder();
    TwitterFactory tf;
    Twitter twitter;

    public TwitterService() { //set twitter authporization.
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("6ZGUP45trWS72HjdwzUzimXId") //Your Twitter App Consumer Key and Consumer Secret
                .setOAuthConsumerSecret("dES6fsLIMsWacqaBXoFWdLT2XZoo3vuF5qQxM6srPVwVvR7hHQ")
                .setOAuthAccessToken("115300522-1gcuzLOiDGbMsxOq2w4rwgbYhnF0lS5kTOo2pSoW") // your Twitter Access Token and Token Screnet
                .setOAuthAccessTokenSecret("V7gzF9FajZQa1ijZc2rvgcpbFgCgnJM1DrMy1pBh0EMAI");
        tf = new TwitterFactory(cb.build());
    }

    @Override
    public void publish(BaseAd ad, User user) {
        if(ad==null || user==null){
            throw new NullPointerException("cant export to twitter. user or ad is null");
        }
        postTweet(ad,user);
    }

    @Override
    public Result preview(BaseAd ad) {
        return null;
    }

    public void postTweet(BaseAd ad, User user){ //After parsed BaseAd to a string, tweet the string
        CommonProfile profile = user.getProfile(User.ProfileKey.TWITTER);
        String accessToken = profile.getAccessToken();
        String accessSecret = profile.getAccessSecret();
        twitter = tf.getInstance();
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessSecret));
        StatusUpdate adtweet = new StatusUpdate(parseAd(ad));
        try {
            if(!ad.getPictureUrls().isEmpty()){
                adtweet.setMedia("AdPhoto",new URL(ad.getPictureUrls().get(0)).openStream());
            }
            twitter.updateStatus(adtweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseAd(BaseAd ad) { // parse Ad obj to a string since twitter udpate method accept String as parameter
        String adTitle = ad.getTitle().replaceAll("[\\-\\+\\.\\^:,]","");
        String adkey = ad.getKeywordString();
        String adinfo = adTitle + adkey;
        if (adinfo.length()>=118) {
            adinfo = adinfo.substring(0,115);
        }
        String shorturl = ad.getAdurl();
        //System.out.println(adinfo+shorturl);
        return adinfo+" "+shorturl;
    }



}
