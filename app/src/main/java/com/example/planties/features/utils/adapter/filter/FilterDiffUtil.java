package com.example.planties.features.utils.adapter.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

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
