package service;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import controllers.Application;
import ext.aws.S3Result;
import ext.aws.S3Service;
import model.BaseAd;
import model.Query;
import model.User;
import model.ad.Ad;
import model.ad.Categories;
import model.ad.Categories.Category;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import play.libs.F;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sasinda on 9/30/15.
 * Useful reference for 'Mongodb java driver' : http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
 */
public class AdService extends BasicMongoService {

    private S3Service s3Service = new S3Service();

    public List<Document> getCategories(String name) {
        String collection = "category";
        List<Document> categories;
        if (name == null || name.equalsIgnoreCase("all")) {
            categories = findAll(collection);
        } else {
            categories = new ArrayList<>();
//          Document catdoc = db.getCollection(collection).findOne();
//          categories.add(catdoc);
        }
        return categories;
    }

    /**
     * @param ad
     * @return
     */
    public Document saveAdByCat(BaseAd ad) {
        String cat = ad.getCategory(); // ad's collection name, eg "vehicle"
        //String adid = ad.getId(); //get ad's unique mongo _id
        if (cat != null) {
            Document doc = asDocument(ad);
            db.getCollection(cat).insertOne(doc);
            ad.set_id(doc.getObjectId("_id"));
            return doc;
        }
        return null;
    }


    public <A extends BaseAd> A saveAd(A ad, User user) {
        ad.setUser(user);
        ad.setCreatedOn(ZonedDateTime.now().toEpochSecond());
        Key<A> save = datastore.save(ad);
        ad.set_id((ObjectId) save.getId());
        return ad;
    }


    public Document getAd(String category, String id) {
        Document myAd = db.getCollection(category).find(eq("_id", new ObjectId(id))).first();
        return myAd;
    }

    public <V extends BaseAd> V getAd(V ad) {
        if (ad.getClass().getSimpleName().equals("BaseAd")) {
            Document myAd = db.getCollection(ad.getCategory()).find(eq("_id", new ObjectId(ad.getId()))).first();
            Class<V> c = (Class<V>) ad.getClass();
            return morphia.fromDBObject(c, new BasicDBObject(myAd));
        }
        return datastore.get(ad);
    }


    public Long deleteAd(String category, String id) {
        DeleteResult id1 = db.getCollection(category).deleteOne(eq("_id", new ObjectId(id)));//http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
        return id1.getDeletedCount();

    }

    public Long deleteAds(String category, Bson query) {
        DeleteResult id1 = db.getCollection(category).deleteMany(query);//http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
        return id1.getDeletedCount();

    }

    public Document updateAd(BaseAd ad) {
        String cat = ad.getCategory(); // ad's collection name, eg "vehicle"
        String adid = ad.getId(); //get ad's unique mongo _id
        ad.setUser(null);
        if (cat != null && adid != null) {
            Document doc = asDocument(ad);
            UpdateResult updateResult = db.getCollection(cat).updateOne(eq("_id", new ObjectId(adid)), new Document("$set", doc));
            return doc;
        }
        return null;
    }


    private List<Document> findAll(String collection) {
        List<Document> docs = new ArrayList<>();
        MongoCursor<Document> cursor = db.getCollection(collection).find().iterator();
        try {
            while (cursor.hasNext()) {
                docs.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return docs;
    }

    /**
     * @param queryString make sure this begins with a ? mark.
     * @return
     */
    public List<Document> queryAds(String queryString) {
        return queryAds(Util.queryStringToMap(queryString));
    }

    public List<Document> queryAds(Map<String, String[]> queryMap) {
        String cat = queryMap.get(BaseAd.CATEGORY)[0];
        Query query = new Query(queryMap);
        FindIterable<Document> documents = db.getCollection(cat).find(query.getFilter()).skip(query.getStart()).limit(query.getLimit());
        return getList(documents);
    }

    public List<Document> queryAds(String category, Bson query, int skip, int limit) {
        FindIterable<Document> documents = db.getCollection(category).find(query).skip(skip).limit(limit);
        return getList(documents);
    }

    public List<Document> searchAds(String searchString) {
        String[] split = searchString.split(" ");
        String cat = split[0];
        try {
            Category category = Category.valueOf(cat);
            return findAll(category.name);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
        return findAll(Category.Vehicle.name);
    }

    /**
     * Fully asynchronous saving of ad pictures to amazon and updating the locally saved ad in mongo db
     *
     * @param ad
     * @param user
     */
    public void savePictures(BaseAd ad, User user) {
        File userDir = new File(Application.PICTURE_FOLDER + user.getUsername());
        Collection<File> files = FileUtils.listFiles(userDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
//        List<File> files= FileStoreService.getFiles(user.getUsername());
        String s3Folder = user.getUsername() + "/" + ad.getId();
        s3Service.createFolder(S3Service.BUCKET_NAME, s3Folder);

        List<F.Promise<S3Result>> promises = new ArrayList<>();
        for (File file : files) {
            F.Promise<S3Result> promise = s3Service.uploadAdImg(file, s3Folder);
            promises.add(promise);
        }
        F.Promise.sequence(promises).onRedeem((s3results) -> {
            BaseAd savedAd = getAd(ad);
            for (S3Result s3result : s3results) {
                savedAd.addPictureUrl(s3result.getUploadUrl());
            }
            updateAd(savedAd);
        });

        try {
            FileUtils.deleteDirectory(userDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        FileStoreService.removeFiles(user.getUsername());
    }

}
