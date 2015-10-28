package model;

import org.apache.commons.lang3.text.WordUtils;
import play.data.validation.Constraints;

import java.math.BigDecimal;

/**
 * Created by sasinda on 10/2/15.
 */
public class Ad extends BaseModel{
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
    private String keywords;
    private String address;
    private String state;
    private String country;
    private String zipcode;
    private BigDecimal price;
    private boolean agree;


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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }
}
