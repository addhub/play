package social;


import model.BaseAd;
import org.junit.Before;
import org.junit.Ignore;

@Ignore
public class FacebookServiceTest {

    static FacebookService facebookService;
    static BaseAd testfacebookad;


    @Before
    public  void setup(){
        testfacebookad = new BaseAd();
        testfacebookad.setTitle("testTweetAd");
        testfacebookad.setCategory("testTweetAd");
        //testfacebookad.setPictureUrl("http://cloudlakes.com/data_images/makers/mersedes-benz/mersedes-benz-01.jpg");
    }
    @Ignore
    @org.junit.Test
    public void testPublishMessage() throws Exception {
        facebookService = new FacebookService();
        facebookService.publishMessage(testfacebookad);
    }

    /*
    @org.junit.Test
    public void testPublishPhoto() throws Exception {
        facebookService = new FacebookService();
        facebookService.publishPhoto(testfacebookad);

    }

    */

    @org.junit.Test
    public void testDelete() throws Exception {
        System.out.println( "test Delete object");

    }
}