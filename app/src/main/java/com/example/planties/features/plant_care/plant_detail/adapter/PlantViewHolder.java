package com.example.planties.features.plant_care.plant_detail.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.databinding.ItemPlantBinding;

public class PlantViewHolder extends RecyclerView.ViewHolder {
    private final ItemPlantBinding binding;

    public PlantViewHolder(ItemPlantBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(PlantModel item) {
        ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getImage());
    }
}
