package model.ad;

import model.BaseAd;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sasinda on 11/22/15.
 */
public class Categories {

    public static final String TBL_VEHICLE ="Vehicle";
    public static final String TBL_FOR_RENT ="ForRent";
    public static final String TBL_PETS ="Pets";
    public static final String TBL_SERVICES ="Services";

    public enum Category{
        Vehicle("Vehicle"),
        ForRent("ForRent"),
        Pets("Pets"),
        Services("Services"),
        Personal("Personal"),
        Dating("Dating"),
        Community("Community"),
        Jobs("Jobs"),
        ItemsForSale("ItemsForSale");

        public final String name;
        Category(String name){
            this.name =name;
        }
    }


    private final static Map<Category,? extends BaseAd> models=new HashMap<>();


    public static Class<? extends BaseAd> getModelType(Category cat){
        return models.get(cat).getClass();
    }
}
