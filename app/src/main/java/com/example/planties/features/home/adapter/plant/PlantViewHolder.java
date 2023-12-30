package com.example.planties.features.home.adapter.plant;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
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
        if(!item.getUrlImage().isEmpty()){
            binding.tvGarden.setText(item.getName());
            ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getUrlImage().get(0));
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item.getGardenId(), item.getId()));
        }else{
            binding.tvGarden.setText(item.getName());
            binding.sivPlant.setImageResource(R.drawable.img_plants);
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item.getGardenId(), item.getId()));
        }
    }
}
