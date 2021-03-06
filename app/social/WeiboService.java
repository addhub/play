package social;

import model.BaseAd;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jingxiapang on 10/29/15.
 */



public class WeiboService {

    public ConfigurationBuilder cb = new ConfigurationBuilder();
    TwitterFactory tf;
    Twitter twitter;

    public WeiboService() { //set twitter authporization.
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("6ZGUP45trWS72HjdwzUzimXId") //Your Twitter App Consumer Key and Consumer Secret
                .setOAuthConsumerSecret("dES6fsLIMsWacqaBXoFWdLT2XZoo3vuF5qQxM6srPVwVvR7hHQ")
                .setOAuthAccessToken("115300522-1gcuzLOiDGbMsxOq2w4rwgbYhnF0lS5kTOo2pSoW") // your Twitter Access Token and Token Screnet
                .setOAuthAccessTokenSecret("V7gzF9FajZQa1ijZc2rvgcpbFgCgnJM1DrMy1pBh0EMAI");
        tf = new TwitterFactory(cb.build());
    }

    public void postTweet(BaseAd ad) throws IOException { //After parsed Ad to a string, tweet the string
        twitter = tf.getInstance();
        StatusUpdate adtweet = new StatusUpdate(parseAd(ad));
        adtweet.setMedia("AdPhoto",new URL(ad.getPictureUrls().get(0)).openStream());
        try {
            twitter.updateStatus(adtweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        //System.out.println("Successfully posted to [" + adtweet.getStatus() + "].");
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
