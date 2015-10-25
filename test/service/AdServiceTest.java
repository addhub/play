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

    @org.junit.Test
    public void testGetAd() throws Exception {
        Ad ad=new Ad();
        ad.setTitle("testGet");
        ad.setCategory("test Get Ad"); // All category word unit is capitalized. Note this when using adService.getAd(categoryName)!
        Document document = adService.postAd(ad); //create one tested ad then get, delete or update
        Document getAdDoc = adService.getAd("TestGetAd", document.getObjectId("_id").toString());
        assertEquals("testGet", getAdDoc.get("title"));
    }

    @org.junit.Test
    public void testDeleteAd() throws Exception { //test Delete Ad method from API - AdService
        Ad ad=new Ad();
        ad.setTitle("testDelete");
        ad.setCategory("TestDeleteAd");
        Document document = adService.postAd(ad);
        long deleteAdDoc = adService.deleteAd("TestDeleteAd", document.getObjectId("_id").toString());
        assertEquals((long)1, deleteAdDoc);
    }

    @org.junit.Test
    public void testUpdateAd() throws Exception {  //test update Ad method from API - AdService
        Ad ad=new Ad();
        ad.setTitle("testUpdate");
        ad.setCategory("TestUpdateAd");
        ad.setZipcode("10011");
        Document document = adService.postAd(ad);

        ad.setZipcode("11122");

        Document updateAd = adService.updateAd(ad);
        assertEquals("11122", updateAd.get("zipcode"));
    }

}