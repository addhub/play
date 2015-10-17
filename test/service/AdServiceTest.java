package service;

import model.Ad;
import org.bson.Document;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by sasinda on 10/7/15.
 */
public class AdServiceTest {

    AdService adService =new AdService();

    @org.junit.Test
    public void testGetCategory() throws Exception {
        List<Document> all = adService.getCategories("all");
        assertNotNull(all);
    }

    @org.junit.Test
    public void testPostAd() throws Exception {
        Ad ad=new Ad();
        ad.setTitle("test ad");
        ad.setCategory("test");
        Document document = adService.postAd(ad);
        assertNotNull(document.get("title"));
    }
}