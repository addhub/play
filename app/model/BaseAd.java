package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import global.AppConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import model.ad.Categories;
import org.apache.commons.lang3.text.WordUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import play.data.validation.Constraints;

import javax.persistence.Embedded;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sasinda on 10/2/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity("BaseAd")
public class BaseAd extends BaseModel{
    public static final String TITLE="title";
    public static final String CATEGORY="category";
    public static final String SUBCAT="subCat";
    public static final String DESCRIPTION="description";

    @Constraints.Required
    private String title;
    @Constraints.Required
    private String category;
    @Constraints.Required
    private String subCat;
    private String description;
    private List<String> keywords=new ArrayList<>();
    private String address;
    private String state;
    private String country;
    private String zipcode;
    private Double price;
    private boolean agree;
    private List<String> pictureUrls=new ArrayList<>();
    private String premiumAd;
    //number of days the add is valid -1 is good till cancelled
    private int goodTill;
    private Long createdOn;
    private Long updatedOn;
    @Embedded
    private List<Export> exports;
    @Reference(lazy = true)
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String cat) {
        cat= WordUtils.capitalize(cat);
        cat=cat.replaceAll("\\s","");
        this.category = cat;
    }

    public String getSubCat() {
        return subCat;
    }

    public void setSubCat(String subCat) {
        subCat= WordUtils.capitalize(subCat);
        subCat=subCat.replaceAll("\\s","");
        this.subCat = subCat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Make sure to convert to Big Decimal when doing aggregate computations.
     * @return price as double
     */
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }



    public String getAdurl(){
        return AppConfig.getString("baseUrl")+"/#/ad/"+getId();
    }


    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public int getGoodTill() {
        return goodTill;
    }

    public void setGoodTill(int goodTill) {
        this.goodTill = goodTill;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public List<Export> getExports() {
        return exports;
    }

    public void setExports(List<Export> exports) {
        this.exports = exports;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKeywordString() {
        return getKeywords().toString();
    }

    public void addPictureUrl(String picUrl) {
        pictureUrls.add(picUrl);
    }
}
