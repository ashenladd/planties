package com.example.planties.features.home.adapter.plant.filter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.databinding.ItemTabFilterBinding;

public class FilterViewHolder extends RecyclerView.ViewHolder {
    private final ItemTabFilterBinding binding;

    public FilterViewHolder(ItemTabFilterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(FilterModel item) {
        binding.btnFilter.setText(item.getName());
    }
}
