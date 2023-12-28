package com.example.planties.features.plant_care.plant_detail.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.databinding.ItemPlantBinding;

public class PlantAdapter extends ListAdapter<PlantModel, PlantViewHolder> {
    public PlantAdapter() {
        super(new PlantDiffUtil());
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlantBinding binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
