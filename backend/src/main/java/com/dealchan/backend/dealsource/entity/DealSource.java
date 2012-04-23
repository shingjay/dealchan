package com.dealchan.backend.dealsource.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/11/12
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "awesome_deals")
public class DealSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String title;

    private double price;
    private double discount;
    private double originalPrice;

    @Lob
    private String description;

    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp dealEnds;

    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Lob
    private String address;
    @Lob
    private String imageUrl;
    @Lob
    private String dealUrl;
    
    private String category;
    
    private String city;
    private String country;


    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    @PreUpdate
    protected void onUpdate() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDealEnds() {
        return dealEnds;
    }

    public void setDealEnds(Timestamp dealEnds) {
        this.dealEnds = dealEnds;
    }

    public String getDealUrl() {
        return dealUrl;
    }

    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DealSource)) return false;

        DealSource source = (DealSource) o;

        if (Double.compare(source.discount, discount) != 0) return false;
        if (Double.compare(source.originalPrice, originalPrice) != 0) return false;
        if (Double.compare(source.price, price) != 0) return false;
        if (address != null ? !address.equals(source.address) : source.address != null) return false;
        if (category != null ? !category.equals(source.category) : source.category != null) return false;
        if (city != null ? !city.equals(source.city) : source.city != null) return false;
        if (country != null ? !country.equals(source.country) : source.country != null) return false;
        if (createdAt != null ? !createdAt.equals(source.createdAt) : source.createdAt != null) return false;
        if (dealEnds != null ? !dealEnds.equals(source.dealEnds) : source.dealEnds != null) return false;
        if (dealUrl != null ? !dealUrl.equals(source.dealUrl) : source.dealUrl != null) return false;
        if (description != null ? !description.equals(source.description) : source.description != null) return false;
        if (id != null ? !id.equals(source.id) : source.id != null) return false;
        if (imageUrl != null ? !imageUrl.equals(source.imageUrl) : source.imageUrl != null) return false;
        if (title != null ? !title.equals(source.title) : source.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        temp = price != +0.0d ? Double.doubleToLongBits(price) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = discount != +0.0d ? Double.doubleToLongBits(discount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = originalPrice != +0.0d ? Double.doubleToLongBits(originalPrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dealEnds != null ? dealEnds.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (dealUrl != null ? dealUrl.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
