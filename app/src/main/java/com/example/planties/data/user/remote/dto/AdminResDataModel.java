package com.example.planties.data.user.remote.dto;

import com.google.gson.annotations.SerializedName;

public class AdminResDataModel {
    @SerializedName("adminPage")
    private AdminResModel adminPage;

    public AdminResModel getAdminPage() {
        return adminPage;
    }

    public void setAdminPage(AdminResModel adminPage) {
        this.adminPage = adminPage;
    }
}
