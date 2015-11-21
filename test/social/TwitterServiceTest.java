package social;

import model.Ad;
import service.AdService;


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
        testtweetad.setPictureUrl("http://cloudlakes.com/data_images/makers/mersedes-benz/mersedes-benz-01.jpg");
        twitterService.postTweet(testtweetad);



    }
}