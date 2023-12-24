package com.example.planties.data.garden.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GardenResModel {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("type")
    private String mType;
    @SerializedName("user_id")
    private String mUserId;

    @SerializedName("url_image")
    private List<String> mUrlImage;

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

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public List<String> getUrlImage() {
        return mUrlImage;
    }

    public void setUrlImage(List<String> urlImage) {
        mUrlImage = urlImage;
    }
}
