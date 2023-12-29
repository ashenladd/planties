package com.example.planties.core;

public enum TanamanType {
    TANAMAN_AIR("Tanaman Air"),
    TANAMAN_DAUN_HIJAU("Tanaman Daun Hijau"),
    TANAMAN_BUAH("Tanaman Buah"),
    TANAMAN_BERBUNGA("Tanaman Berbunga");

    private final String value;

    TanamanType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
