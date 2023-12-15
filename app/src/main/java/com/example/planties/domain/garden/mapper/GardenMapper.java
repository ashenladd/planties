package com.example.planties.domain.garden.mapper;

import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.domain.garden.model.GardenModel;

import java.util.ArrayList;
import java.util.List;

public class GardenMapper {

    public static List<GardenModel> mapToModelList(List<GardenResModel> gardenResModelList) {
        List<GardenModel> gardenModelList = new ArrayList<>();

        if (gardenResModelList != null) {
            for (GardenResModel gardenResModel : gardenResModelList) {
                GardenModel gardenModel = mapToModel(gardenResModel);
                gardenModelList.add(gardenModel);
            }
        }

        return gardenModelList;
    }

    public static GardenModel mapToModel(GardenResModel gardenResModel) {
        if (gardenResModel == null) {
            return null;
        }

        GardenModel gardenModel = new GardenModel();
        gardenModel.setId(gardenResModel.getId());
        gardenModel.setName(gardenResModel.getName());
        gardenModel.setType(gardenResModel.getType());
        gardenModel.setUserId(gardenResModel.getUserId());

        return gardenModel;
    }
}