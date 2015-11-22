package social;

import model.Ad;
import service.AdService;

import java.util.ArrayList;

/**
 * Created by jingxiapang on 10/28/15.
 */

public class TwitterServiceTest {
    static TwitterService twitterService;
    static Ad testtweetad;
    static AdService adService;

    @org.junit.Test
    public void testPostTweet() throws Exception {
        twitterService = new TwitterService();
        testtweetad = new Ad();
        adService=new AdService();
        testtweetad.setTitle("testTweetAd");
        testtweetad.setCategory("testTweetAd");
        ArrayList<String> pics = new ArrayList<>();
        pics.add("http://cloudlakes.com/data_images/makers/mersedes-benz/mersedes-benz-01.jpg");
        testtweetad.setPictureUrls(pics);
        twitterService.postTweet(testtweetad);
    }
}