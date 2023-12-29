package com.example.planties.features.plant_care.adapter.garden;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.planties.domain.garden.model.GardenModel;

public class GardenDiffUtil extends DiffUtil.ItemCallback<GardenModel> {
    @Override
    public boolean areItemsTheSame(@NonNull GardenModel oldItem, @NonNull GardenModel newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull GardenModel oldItem, @NonNull GardenModel newItem) {
        return oldItem.getName().equals(newItem.getName()) &&
                oldItem.getUrlImage().equals(newItem.getUrlImage());
    }
}
