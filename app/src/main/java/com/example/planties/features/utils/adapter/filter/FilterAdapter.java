package com.example.planties.features.utils.adapter.filter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.databinding.ItemTabFilterBinding;

public class FilterAdapter extends ListAdapter<FilterModel, FilterViewHolder> {
    private final FilterListener filterListener;

    public FilterAdapter(FilterListener filterListener) {
        super(new FilterDiffUtil());
        this.filterListener = filterListener;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTabFilterBinding binding = ItemTabFilterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        holder.bind(getItem(position),filterListener);
    }
}
