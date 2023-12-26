package com.example.planties.core;

public enum GardenType {
    INDOOR("INDOOR"),
    OUTDOOR("OUTDOOR");
    private final String value;
    GardenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
