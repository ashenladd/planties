package com.example.planties.data.user.remote.dto;

import com.google.gson.annotations.SerializedName;

public class AdminResModel {
    @SerializedName("Tanaman Buah")
    private double tanamanBuah;

    @SerializedName("Tanaman Air")
    private double tanamanAir;

    @SerializedName("Tanaman Daun Hijau")
    private double tanamanDaunHijau;

    @SerializedName("Tanaman Berbunga")
    private double tanamanBerbunga;

    @SerializedName("totalUser")
    private int totalUser;

    @SerializedName("totalNewUser")
    private int totalNewUser;

    public double getTanamanAir() {
        return tanamanAir;
    }

    public double getTanamanBerbunga() {
        return tanamanBerbunga;
    }

    public double getTanamanBuah() {
        return tanamanBuah;
    }

    public double getTanamanDaunHijau() {
        return tanamanDaunHijau;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public int getTotalNewUser() {
        return totalNewUser;
    }


}
