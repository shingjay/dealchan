package com.dealchan.backend.dealsites.streetdeal.entity;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: ong0
 * Date: 4/5/12
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class StreetdealDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @Lob
    private String link;

    @Lob
    @Basic
    private String description;

    private Date validDate;
    private Date pubDate;
    private String city;
    private double currentPrice;
    private double discount;
    private double saving;
    private double originalPrice;
    private Timestamp timeEnds;
    private String extraInformation;
    private boolean active;
    private int bought;
    private String image;
    private String address;
    private int purchased;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StreetdealDeal that = (StreetdealDeal) o;

        if (active != that.active) return false;
        if (bought != that.bought) return false;
        if (Double.compare(that.currentPrice, currentPrice) != 0) return false;
        if (Double.compare(that.discount, discount) != 0) return false;
        if (id != that.id) return false;
        if (Double.compare(that.originalPrice, originalPrice) != 0) return false;
        if (purchased != that.purchased) return false;
        if (Double.compare(that.saving, saving) != 0) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (extraInformation != null ? !extraInformation.equals(that.extraInformation) : that.extraInformation != null)
            return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;
        if (timeEnds != null ? !timeEnds.equals(that.timeEnds) : that.timeEnds != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (validDate != null ? !validDate.equals(that.validDate) : that.validDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (validDate != null ? validDate.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        temp = currentPrice != +0.0d ? Double.doubleToLongBits(currentPrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = discount != +0.0d ? Double.doubleToLongBits(discount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = saving != +0.0d ? Double.doubleToLongBits(saving) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = originalPrice != +0.0d ? Double.doubleToLongBits(originalPrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (timeEnds != null ? timeEnds.hashCode() : 0);
        result = 31 * result + (extraInformation != null ? extraInformation.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + bought;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + purchased;
        return result;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSaving() {
        return saving;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }

    public Timestamp getTimeEnds() {
        return timeEnds;
    }

    public void setTimeEnds(Timestamp timeEnds) {
        this.timeEnds = timeEnds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }
    
    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        return "StreetdealDeal{" +
                "title='" + title + '\'' +
                '}';
    }
}
