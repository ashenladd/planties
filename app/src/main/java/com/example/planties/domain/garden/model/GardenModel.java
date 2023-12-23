package com.example.planties.domain.garden.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GardenModel {
    private String id;
    private String name;
    private String type;
    private String userId;
    private List<String> urlImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<String> getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(List<String> urlImage) {
        this.urlImage = urlImage;
    }
}
