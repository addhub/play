package jobs;

import com.mongodb.client.model.Filters;
import model.ad.Categories;
import service.AdService;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by sasinda on 12/7/15.
 */
public class AdCleanerJob implements Runnable{
    AdService adService=new AdService();
    @Override
    public void run() {
        //do for all categories
        ZonedDateTime now=ZonedDateTime.now();

        for (Categories.Category category : Categories.Category.values()) {
            String cat=category.name;
            Long dels7 = adService.deleteAds(cat, Filters.lt("createdOn", now.minusDays(7).toEpochSecond()));
            System.out.println("7 days old: Deleted "+ dels7+" ads in"+cat);

            Long dels14 = adService.deleteAds(cat, Filters.lt("createdOn", now.minusDays(7).toEpochSecond()));
            System.out.println("14 days old: Deleted "+ dels14+" ads in"+cat);

            Long dels30 = adService.deleteAds(cat, Filters.lt("createdOn", now.minusDays(7).toEpochSecond()));
            System.out.println("30 days old: Deleted "+ dels30+" ads in"+cat);
        }


    }
}
