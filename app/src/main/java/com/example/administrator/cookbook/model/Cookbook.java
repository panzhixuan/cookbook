package com.example.administrator.cookbook.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class Cookbook extends BmobObject {
    String cb_tips;
    BmobRelation likes;
    String cb_ingredint;
    String cb_content;
    String cb_nutrition;
    BmobFile cb_image;
    String cb_name;
    User user;
    Occasion occasion;
    Taste taste;
    Cuisine cuisine;

    public String getCb_tips() {
        return cb_tips;
    }

    public BmobRelation getLikes() {
        return likes;
    }

    public String getCb_ingredint() {
        return cb_ingredint;
    }

    public String getCb_content() {
        return cb_content;
    }

    public String getCb_nutrition() {
        return cb_nutrition;
    }

    public BmobFile getCb_image() {
        return cb_image;
    }

    public String getCb_name() {
        return cb_name;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public Taste getTaste() {
        return taste;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public User getUser() {
        return user;
    }

    public void setCb_tips(String cb_tips) {
        this.cb_tips = cb_tips;
    }

    public void setLikes(BmobRelation likes) {
        this.likes = likes;
    }

    public void setCb_ingredint(String cb_ingredint) {
        this.cb_ingredint = cb_ingredint;
    }

    public void setCb_content(String cb_content) {
        this.cb_content = cb_content;
    }

    public void setCb_nutrition(String cb_nutrition) {
        this.cb_nutrition = cb_nutrition;
    }

    public void setCb_image(BmobFile cb_image) {
        this.cb_image = cb_image;
    }

    public void setCb_name(String cb_name) {
        this.cb_name = cb_name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }
}
