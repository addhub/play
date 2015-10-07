package service;

import model.Ad;
import org.bson.Document;
import org.junit.BeforeClass;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by sasinda on 10/7/15.
 */
public class MongoTest {

    Mongo mongo=new Mongo();

    @org.junit.Test
    public void testGetCategory() throws Exception {
        List<Document> all = mongo.getCategory("all");
        assertNotNull(all);
    }

    @org.junit.Test
    public void testPostAd() throws Exception {
        Ad ad=new Ad();
        ad.setTitle("test ad");
        ad.setCategory("test");
        Document document = mongo.postAd(ad);
        assertNotNull(document.get("title"));
    }
}