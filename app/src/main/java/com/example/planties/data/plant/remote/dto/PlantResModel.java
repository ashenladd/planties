package com.example.planties.data.plant.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantResModel {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("banner")
    private String mBanner;
    @SerializedName("url_image")
    private List<String> mUrlImage;
    @SerializedName("garden_id")
    private String mGardenId;
    @SerializedName("oxygen")
    private Double mOxygen;
    @SerializedName("user_id")
    private String mUserId;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBanner() {
        return mBanner;
    }

    public void setBanner(String banner) {
        mBanner = banner;
    }

    public List<String> getUrlImage() {
        return mUrlImage;
    }

    public void setUrlImage(List<String> urlImage) {
        mUrlImage = urlImage;
    }

    public String getGardenId() {
        return mGardenId;
    }

    public void setGardenId(String gardenId) {
        mGardenId = gardenId;
    }
    public Double getOxygen() {
        return mOxygen;
    }

    public void setOxygen(Double oxygen) {
        mOxygen = oxygen;
    }
    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }
}
