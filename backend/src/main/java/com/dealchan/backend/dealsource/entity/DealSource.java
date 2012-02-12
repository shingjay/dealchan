package com.dealchan.backend.dealsource.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/11/12
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class DealSource {

    @Id
    private Long id;
    @Lob
    private String title;

    private double price;
    private double discount;
    private double originalPrice;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dealEnds;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    @Lob
    private String address;
    @Lob
    private String imageUrl;
    @Lob
    private String dealUlr;
    
    private String category;
    
    private String city;
    private String country;


    @PrePersist
    protected void onCreate() {
        createdAt = Calendar.getInstance();
    }

    @PreUpdate
    protected void onUpdate() {

    }



}
