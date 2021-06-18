package com.contactmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;
    private String name;
    private String phone;
    private String email;
    private String city;
    @Column(length = 300)
    private String description;
    private String imageurl;
    @ManyToOne
    private User user;

    public Contact(){
        super();
    }


    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

 
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

 
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}