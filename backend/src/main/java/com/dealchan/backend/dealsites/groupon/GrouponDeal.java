package com.dealchan.backend.dealsites.groupon;

import java.sql.Time;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 8:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrouponDeal {
    
    private String title;
    private String link;
    private String description;
    private Date pubDate;
    private String city;
    private double currentPrice;
    private double discount;
    private double saving;
    private double originalPrice;
    private String timeLeft;
    private String extraInformation;
    private boolean active;
    private boolean bought;
    private String image;
    private String quote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrouponDeal)) return false;

        GrouponDeal that = (GrouponDeal) o;

        if (active != that.active) return false;
        if (bought != that.bought) return false;
        if (Double.compare(that.currentPrice, currentPrice) != 0) return false;
        if (Double.compare(that.discount, discount) != 0) return false;
        if (Double.compare(that.originalPrice, originalPrice) != 0) return false;
        if (Double.compare(that.saving, saving) != 0) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (extraInformation != null ? !extraInformation.equals(that.extraInformation) : that.extraInformation != null)
            return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;
        if (timeLeft != null ? !timeLeft.equals(that.timeLeft) : that.timeLeft != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
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
        result = 31 * result + (timeLeft != null ? timeLeft.hashCode() : 0);
        result = 31 * result + (extraInformation != null ? extraInformation.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (bought ? 1 : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
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

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
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

    @Override
    public String toString() {
        return "GrouponDeal{" +
                "title='" + title + '\'' +
                '}';
    }
}
