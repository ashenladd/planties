package com.example.planties.features.plant_care.plant_detail.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.databinding.ItemPlantBinding;

public class PlantAdapter extends ListAdapter<PlantModel, PlantViewHolder> {
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    public PlantAdapter(ActivityResultLauncher<PickVisualMediaRequest> pickMedia) {
        super(new PlantDiffUtil());
        this.pickMedia = pickMedia;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlantBinding binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlantViewHolder(binding, pickMedia);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
