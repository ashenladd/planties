package com.example.planties.features.home.adapter.plant.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.planties.domain.garden.model.GardenModel;

public class FilterDiffUtil extends DiffUtil.ItemCallback<FilterModel> {

    @Override
    public boolean areItemsTheSame(@NonNull FilterModel oldItem, @NonNull FilterModel newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull FilterModel oldItem, @NonNull FilterModel newItem) {
        return oldItem.getName().equals(newItem.getName()) &&
                oldItem.isSelected() == newItem.isSelected();
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull FilterModel oldItem, @NonNull FilterModel newItem) {
        return newItem;
    }
}
