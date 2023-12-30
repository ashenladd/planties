package com.example.planties.features.plant_care.plant_detail.adapter;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.core.utils.ImageUtils;
import com.example.planties.databinding.ItemPlantBinding;

import java.util.Objects;

public class PlantViewHolder extends RecyclerView.ViewHolder {
    private final ItemPlantBinding binding;
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    public PlantViewHolder(ItemPlantBinding binding, ActivityResultLauncher<PickVisualMediaRequest> pickMedia) {
        super(binding.getRoot());
        this.binding = binding;
        this.pickMedia = pickMedia;
    }

    public void bind(PlantModel item) {
        if (item.getImage().equals("Add")) {
            binding.sivPlant.setImageResource(R.drawable.img_add_photo);
            binding.sivPlant.setOnClickListener(v -> {
                launchPhotoPicker();
            });
        } else if (!Objects.equals(item.getImage(), "")) {
            if (item.getImage().contains("http")) {
                ImageExtensions.loadPlantImage(binding.sivPlant, binding.getRoot().getContext(), item.getImage());
            } else {
                binding.sivPlant.setImageBitmap(ImageUtils.convertBase64ToImage(item.getImage()));
            }
        }
    }

    private void launchPhotoPicker() {
        PickVisualMediaRequest pickVisualMediaRequest = new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build();
        pickMedia.launch(pickVisualMediaRequest);
    }
}
