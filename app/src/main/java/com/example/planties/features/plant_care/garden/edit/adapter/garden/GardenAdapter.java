package com.example.planties.features.plant_care.garden.edit.adapter.garden;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.databinding.ItemPlantBinding;

public class GardenAdapter extends ListAdapter<GardenGalleryModel, GardenViewHolder> {

    public GardenAdapter() {
        super(new GardenDiffUtil());
    }

    @NonNull
    @Override
    public GardenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlantBinding binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GardenViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GardenViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
