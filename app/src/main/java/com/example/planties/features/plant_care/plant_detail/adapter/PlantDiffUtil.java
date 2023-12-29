package com.example.planties.features.plant_care.plant_detail.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.planties.data.plant.remote.dto.PlantResModel;

public class PlantDiffUtil extends DiffUtil.ItemCallback<PlantModel> {

    @Override
    public boolean areItemsTheSame(@NonNull PlantModel oldItem, @NonNull PlantModel newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull PlantModel oldItem, @NonNull PlantModel newItem) {
        return oldItem.getImage().equals(newItem.getImage());
    }
}
