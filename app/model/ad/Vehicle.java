package model.ad;

import model.BaseAd;
import org.mongodb.morphia.annotations.Entity;


/**
 * Created by sasinda on 11/22/15.
 */
@Entity(Categories.TBL_VEHICLE)
public class Vehicle extends BaseAd implements Ad {


}
