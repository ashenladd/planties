package com.example.planties.features.home.adapter.plant;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.domain.garden.model.GardenModel;

public class PlantDiffUtil extends DiffUtil.ItemCallback<PlantResModel> {

    @Override
    public boolean areItemsTheSame(@NonNull PlantResModel oldItem, @NonNull PlantResModel newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull PlantResModel oldItem, @NonNull PlantResModel newItem) {
        return oldItem.getName().equals(newItem.getName()) &&
                oldItem.getUrlImage().equals(newItem.getUrlImage());
    }
}
