package com.example.planties.features.plant_care.garden.edit.adapter.plant;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.databinding.ItemPlantBinding;

import java.util.Objects;

public class PlantViewHolder extends RecyclerView.ViewHolder {
    private final ItemPlantBinding binding;

    public PlantViewHolder(ItemPlantBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(PlantResModel item, PlantListener listener) {
        float dip = 100f;
        Resources r = itemView.getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        if (!Objects.equals(item.getId(), "Add")) {
            if (item.getUrlImage().isEmpty()) {
                binding.sivPlant.getLayoutParams().width = ((int) px);
                binding.sivPlant.requestLayout();
                binding.tvGarden.setText(item.getName());
            } else {
                binding.sivPlant.getLayoutParams().width = ((int) px);
                binding.sivPlant.requestLayout();
                binding.tvGarden.setText(item.getName());
                ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getUrlImage().get(0));
            }
        } else {
            binding.sivPlant.setImageResource(R.drawable.img_add_plant);
            Log.d("PlantViewHolder", "bind: " + item.getGardenId());
            binding.sivPlant.setOnClickListener(v -> listener.onItemClick(item.getGardenId()));
        }
    }
}
