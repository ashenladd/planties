package com.example.planties.features.utils.adapter.sort;

import com.example.planties.domain.garden.model.GardenModel;

import java.util.ArrayList;
import java.util.List;

public class SortModel {
    private String id;
    private String name;
    private boolean isSelected;

    public SortModel(String id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isSelected() {
        return isSelected;
    }

}
