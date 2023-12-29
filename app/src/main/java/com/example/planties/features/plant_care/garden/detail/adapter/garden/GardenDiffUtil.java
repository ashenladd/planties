package com.example.planties.features.plant_care.garden.detail.adapter.garden;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.planties.domain.garden.model.GardenModel;

public class GardenDiffUtil extends DiffUtil.ItemCallback<GardenPhotosModel> {
    @Override
    public boolean areItemsTheSame(@NonNull GardenPhotosModel oldItem, @NonNull GardenPhotosModel newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull GardenPhotosModel oldItem, @NonNull GardenPhotosModel newItem) {
        return oldItem.getImage().equals(newItem.getImage());
    }
}
