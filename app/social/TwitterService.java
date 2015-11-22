package social;

import model.Ad;
import play.Play;
import play.mvc.Result;
import play.mvc.Results;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
    public void publish(Ad ad) {
        postTweet(ad);
    }

    @Override
    public Result preview(Ad ad) {
        return null;
    }

    public void postTweet(Ad ad){ //After parsed Ad to a string, tweet the string
        twitter = tf.getInstance();
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



}
