package com.example.planties.features.utils.adapter.sort;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class SortDiffUtil extends DiffUtil.ItemCallback<SortModel> {

    @Override
    public boolean areItemsTheSame(@NonNull SortModel oldItem, @NonNull SortModel newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull SortModel oldItem, @NonNull SortModel newItem) {
        return oldItem.getName().equals(newItem.getName()) &&
                oldItem.isSelected() == newItem.isSelected();
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull SortModel oldItem, @NonNull SortModel newItem) {
        return newItem;
    }
}
