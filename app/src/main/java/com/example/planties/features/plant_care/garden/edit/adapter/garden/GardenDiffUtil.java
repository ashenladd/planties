package com.example.planties.features.plant_care.garden.edit.adapter.garden;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class GardenDiffUtil extends DiffUtil.ItemCallback<GardenGalleryModel> {
    @Override
    public boolean areItemsTheSame(@NonNull GardenGalleryModel oldItem, @NonNull GardenGalleryModel newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull GardenGalleryModel oldItem, @NonNull GardenGalleryModel newItem) {
        return oldItem.getImage().equals(newItem.getImage());
    }
}
