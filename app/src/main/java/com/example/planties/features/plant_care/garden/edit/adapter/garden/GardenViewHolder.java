package com.example.planties.features.plant_care.garden.edit.adapter.garden;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.databinding.ItemPlantBinding;

public class GardenViewHolder extends RecyclerView.ViewHolder {
    private final ItemPlantBinding binding;

    public GardenViewHolder(ItemPlantBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(GardenGalleryModel item) {
        if (item.getImage() != "Add") {
            ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getImage());
        }else{
            binding.sivPlant.setImageResource(R.drawable.img_add_photo);
        }
    }
}
