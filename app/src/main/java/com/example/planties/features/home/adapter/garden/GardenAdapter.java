package com.example.planties.features.home.adapter.garden;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.databinding.ItemGardenBinding;
import com.example.planties.domain.garden.model.GardenModel;

public class GardenAdapter extends ListAdapter<GardenModel, GardenViewHolder> {
    private final GardenListener listener;

    public GardenAdapter(GardenListener listener) {
        super(new GardenDiffUtil());
        this.listener = listener;
    }

    @NonNull
    @Override
    public GardenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGardenBinding binding = ItemGardenBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GardenViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GardenViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }
}
