package com.example.planties.features.home.adapter.plant.plants;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.databinding.ItemGardenBinding;
import com.example.planties.databinding.ItemPlantBinding;
import com.example.planties.domain.garden.model.GardenModel;

public class PlantViewHolder extends RecyclerView.ViewHolder {
    private final ItemPlantBinding binding;

    public PlantViewHolder(ItemPlantBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(PlantResModel item, PlantListener listener) {
        binding.tvGarden.setText(item.getName());
        ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getUrlImage().get(0));
        binding.getRoot().setOnClickListener(v -> listener.onItemClick(item.getId()));
    }
}
