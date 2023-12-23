package com.example.planties.domain.garden.mapper;

import com.example.planties.data.garden.remote.dto.GardenResDetailDataModel;
import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.domain.garden.model.GardenModel;

import java.util.ArrayList;
import java.util.List;

public class GardenMapper {

    public static List<GardenModel> mapToModelList(List<GardenResModel> gardenResDetailDataModelList) {
        List<GardenModel> gardenModelList = new ArrayList<>();

        if (gardenResDetailDataModelList != null) {
            for (GardenResModel gardenResModel : gardenResDetailDataModelList) {
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
        gardenModel.setUrlImage(gardenResModel.getUrlImage());

        return gardenModel;
    }
}