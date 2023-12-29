package com.example.planties.features.plant_care.garden.edit.adapter.garden;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.databinding.ItemPlantBinding;

public class GardenAdapter extends ListAdapter<GardenGalleryModel, GardenViewHolder> {
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    public GardenAdapter(ActivityResultLauncher<PickVisualMediaRequest> pickMedia) {
        super(new GardenDiffUtil());
        this.pickMedia = pickMedia;
    }

    @NonNull
    @Override
    public GardenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlantBinding binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GardenViewHolder(binding,pickMedia);
    }

    @Override
    public void onBindViewHolder(@NonNull GardenViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
