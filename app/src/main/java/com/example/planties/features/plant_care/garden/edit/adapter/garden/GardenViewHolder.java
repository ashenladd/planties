package com.example.planties.features.plant_care.garden.edit.adapter.garden;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.databinding.ItemPlantBinding;

import java.util.Objects;

public class GardenViewHolder extends RecyclerView.ViewHolder {
    private final ItemPlantBinding binding;
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    public GardenViewHolder(ItemPlantBinding binding, ActivityResultLauncher<PickVisualMediaRequest> pickMedia) {
        super(binding.getRoot());
        this.binding = binding;
        this.pickMedia = pickMedia;
    }

    public void bind(GardenGalleryModel item) {
        if (!Objects.equals(item.getImage(), "Add")) {
            ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getImage());
        } else {
            binding.sivPlant.setImageResource(R.drawable.img_add_photo);
            binding.sivPlant.setOnClickListener(v -> launchPhotoPicker());
        }
    }

    private void launchPhotoPicker() {
        PickVisualMediaRequest pickVisualMediaRequest = new PickVisualMediaRequest.Builder().build();
        pickMedia.launch(pickVisualMediaRequest);
    }
}
