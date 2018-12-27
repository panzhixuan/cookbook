package com.example.administrator.cookbook.model;

import cn.bmob.v3.BmobObject;

public class Cuisine extends BmobObject {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
