package service;

import model.BaseAd;
import model.User;
import model.ad.Vehicle;
import org.bson.Document;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by sasinda on 10/7/15.
 */
public class BaseAdServiceTest {

    AdService adService =new AdService();
    UserService userService=new UserService();

    @org.junit.Test
    public void testGetCategory() throws Exception {
        List<Document> all = adService.getCategories("all");
        assertNotNull(all);
    }

    @org.junit.Test
    public void testSaveAdByCat() throws Exception {
        BaseAd ad=new BaseAd();
        ad.setTitle("test ad");
        ad.setCategory("TESTAD");
        Document document = adService.saveAdByCat(ad);
        assertNotNull(document.get("title"));
    }

    @org.junit.Test
    public void testSaveAdWithUserAndGet() throws Exception {
        BaseAd ad=new Vehicle();
        ad.setTitle("test ad");
        ad.setCategory("TESTAD");

        User user = userService.getUser("117314158");

        BaseAd result = adService.saveAd(ad, user);
        BaseAd savedAd = adService.getAd(ad);
        assertNotNull(savedAd.getUser());
    }

    @org.junit.Test
    public void testGetAd() throws Exception {
        BaseAd ad=new BaseAd();
        ad.setTitle("testGet");
        ad.setCategory("test Get Ad"); // All category word unit is capitalized. Note this when using adService.getAd(categoryName)!
        Document document = adService.saveAdByCat(ad); //create one tested ad then get, delete or update
        Document getAdDoc = adService.getAd("TestGetAd", document.getObjectId("_id").toString());
        assertEquals("testGet", getAdDoc.get("title"));
    }

    @org.junit.Test
    public void testGetAdModel() throws Exception {
        Vehicle ad=new Vehicle();
        ad.setId("56310b651792c62cd39d4f03");
        Vehicle fullAd = adService.getAd(ad);
        assertEquals("Vehicle",fullAd.getCategory());
    }




    @org.junit.Test
    public void testDeleteAd() throws Exception { //test Delete Ad method from API - AdService
        BaseAd ad=new BaseAd();
        ad.setTitle("testDelete");
        ad.setCategory("TESTAD");
        Document document = adService.saveAdByCat(ad);
        long deleteAdDoc = adService.deleteAd("TestDeleteAd", document.getObjectId("_id").toString());
        assertEquals((long)1, deleteAdDoc);
    }

    @org.junit.Test
    public void testUpdateAd() throws Exception {  //test update Ad method from API - AdService
        BaseAd ad=new BaseAd();
        ad.setTitle("testUpdate");
        ad.setCategory("TESTAD");
        ad.setZipcode("10011");

        Document document = adService.saveAdByCat(ad);

        ad.setZipcode("11122");
        ad.addPictureUrl("test pic");

        Document updateAd = adService.updateAd(ad);
        assertEquals("11122", updateAd.get("zipcode"));
    }

}