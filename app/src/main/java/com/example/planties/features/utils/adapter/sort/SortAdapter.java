package com.example.planties.features.utils.adapter.sort;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.databinding.ItemTabFilterBinding;

public class SortAdapter extends ListAdapter<SortModel, SortViewHolder> {
    private final SortListener sortListener;

    public SortAdapter(SortListener sortListener) {
        super(new SortDiffUtil());
        this.sortListener = sortListener;
    }

    @NonNull
    @Override
    public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTabFilterBinding binding = ItemTabFilterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SortViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
        holder.bind(getItem(position), sortListener);
    }
}
