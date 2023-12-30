package com.example.planties.features.home.adapter.garden;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.databinding.ItemGardenBinding;
import com.example.planties.domain.garden.model.GardenModel;

public class GardenViewHolder extends RecyclerView.ViewHolder {
    private final ItemGardenBinding binding;

    public GardenViewHolder(ItemGardenBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(GardenModel item, GardenListener listener) {
        if (!item.getUrlImage().isEmpty()){
            binding.tvGarden.setText(item.getName());
            ImageExtensions.loadPlantImage(binding.sivGarden, binding.getRoot().getContext(), item.getUrlImage().get(0));
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item.getId()));
        }else{
            binding.tvGarden.setText(item.getName());
            binding.sivGarden.setImageResource(R.drawable.img_garden);
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item.getId()));
        }
    }
}
