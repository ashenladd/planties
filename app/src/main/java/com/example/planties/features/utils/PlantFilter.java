package com.example.planties.features.utils;

import com.example.planties.features.utils.adapter.filter.FilterModel;

import java.util.List;

public class PlantFilter {
    private String sorting;
    private String type;

    public PlantFilter(String sorting, String type) {
        this.sorting = sorting;
        this.type = type;
    }

    public String getSorting() {
        return sorting;
    }

    public String getType() {
        return type;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public void setType(String type) {
        this.type = type;
    }
}
