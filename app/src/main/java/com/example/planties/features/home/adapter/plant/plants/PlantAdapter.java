package com.example.planties.features.home.adapter.plant.plants;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.databinding.ItemGardenBinding;
import com.example.planties.databinding.ItemPlantBinding;
import com.example.planties.domain.garden.model.GardenModel;

public class PlantAdapter extends ListAdapter<PlantResModel, PlantViewHolder> {
    private final PlantListener listener;

    public PlantAdapter(PlantListener listener) {
        super(new PlantDiffUtil());
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlantBinding binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }
}