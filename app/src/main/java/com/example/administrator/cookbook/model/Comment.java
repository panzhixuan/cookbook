package com.example.administrator.cookbook.model;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {
    Cookbook cookbook;
    User author;
    String cm_content;

    public Cookbook getCookbook() {
        return cookbook;
    }

    public User getAuthor() {
        return author;
    }

    public String getCm_content() {
        return cm_content;
    }

    public void setCookbook(Cookbook cookbook) {
        this.cookbook = cookbook;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCm_content(String cm_content) {
        this.cm_content = cm_content;
    }
}
