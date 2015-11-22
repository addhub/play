package model;

import java.time.ZonedDateTime;

/**
 * Created by sasinda on 11/21/15.
 */
public class Export {
    String url;
    ExportTo type;
    ZonedDateTime publishedOn;
    ZonedDateTime lastUpdatedOn;


    public enum ExportTo {
        TWITTER(1),
        FACEBOOK(2),
        GOOGLE_PLUS(3),
        PRINTEREST(4),
        INSTAGRAM(5),
        PDF(1000);

        public int val;
        public static int SOCIAL=100;
        public int AD_SITE=500;
        public int PAPER=1000;
        ExportTo(int val){
            this.val=val;
        }
    }
}
