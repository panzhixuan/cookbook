package com.example.administrator.cookbook.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {
    Occasion user_occasion;
    Cuisine user_cuision;
    BmobRelation browse;
    String user_place;
    BmobFile user_image;
    String user_nickname;
    Taste user_taste;
    BmobRelation likes;

    public Occasion getUser_occasion() {
        return user_occasion;
    }

    public Cuisine getUser_cuision() {
        return user_cuision;
    }

    public BmobRelation getBrowse() {
        return browse;
    }

    public String getUser_place() {
        return user_place;
    }

    public BmobFile getUser_image() {
        return user_image;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public Taste getUser_taste() {
        return user_taste;
    }

    public BmobRelation getLikes() {
        return likes;
    }

    public void setUser_occasion(Occasion user_occasion) {
        this.user_occasion = user_occasion;
    }

    public void setUser_taste(Taste user_taste) {
        this.user_taste = user_taste;
    }

    public void setBrowse(BmobRelation browse) {
        this.browse = browse;
    }

    public void setUser_place(String user_place) {
        this.user_place = user_place;
    }

    public void setUser_image(BmobFile user_image) {
        this.user_image = user_image;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public void setUser_cuision(Cuisine user_cuision) {
        this.user_cuision = user_cuision;
    }

    public void setLikes(BmobRelation likes) {
        this.likes = likes;
    }
}
