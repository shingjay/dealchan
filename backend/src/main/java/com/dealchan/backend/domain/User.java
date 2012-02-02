package com.dealchan.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/1/12
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id")
    private Long id;

    @Basic
    @Column(name = "username")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // remember to override hashCode and equals method ----> important optimization
}
