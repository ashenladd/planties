package com.example.planties.features.plant_care.garden.detail.adapter.garden;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.databinding.ItemGardenBinding;
import com.example.planties.databinding.ItemPhotosBinding;
import com.example.planties.domain.garden.model.GardenModel;

public class GardenViewHolder extends RecyclerView.ViewHolder {
    private final ItemPhotosBinding binding;

    public GardenViewHolder(ItemPhotosBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(GardenPhotosModel item) {
        ImageExtensions.loadPlantImage(binding.ivPicture, binding.getRoot().getContext(), item.getImage());
    }
}
