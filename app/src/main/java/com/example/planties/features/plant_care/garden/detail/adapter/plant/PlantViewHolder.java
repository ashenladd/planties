package com.example.planties.features.plant_care.garden.detail.adapter.plant;

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

    public void bind(PlantResModel item, PlantListener listener) {
        binding.tvGarden.setText(item.getName());
        ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getUrlImage().get(0));
        binding.getRoot().setOnClickListener(v -> listener.onItemClick(item.getGardenId(),item.getId(),false));
    }
}
