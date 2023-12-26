package com.example.planties.features.utils.adapter.filter;

import com.example.planties.domain.garden.model.GardenModel;

import java.util.ArrayList;
import java.util.List;

public class FilterModel {
    private String id;
    private String name;
    private boolean isSelected;

    public FilterModel(String id, String name, boolean isSelected) {
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

    public static FilterModel fromGardenModel(GardenModel gardenModel) {
        return new FilterModel(
                gardenModel.getId(),
                gardenModel.getName(),
                false
        );
    }
    public static List<FilterModel> fromGardenModelList(List<GardenModel> gardenModelList) {
        List<FilterModel> filterList = new ArrayList<>();
        for(GardenModel garden : gardenModelList){
            FilterModel filtermodel = FilterModel.fromGardenModel(garden);
            filterList.add(filtermodel);
        }
        return filterList;
    }
}
