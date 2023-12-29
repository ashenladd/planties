package com.example.planties.features.utils.adapter.filter;

import android.graphics.Color;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
import com.example.planties.databinding.ItemTabFilterBinding;

public class FilterViewHolder extends RecyclerView.ViewHolder {
    private final ItemTabFilterBinding binding;

    public FilterViewHolder(ItemTabFilterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(FilterModel item, FilterListener listener) {
        binding.btnFilter.setOnClickListener(v -> {
            Log.d("FilterViewHolder", "bind: " + item.getName() + " " + item.isSelected());
            listener.onItemClick(item);
        });
        if (item.isSelected()) {
            binding.btnFilter.setBackgroundResource(R.drawable.bg_button_primary);
            binding.btnFilter.setTextColor(Color.WHITE);
        } else {
            binding.btnFilter.setBackgroundResource(R.drawable.bg_button_netral1_active);
            binding.btnFilter.setTextColor(Color.BLACK);
        }
        binding.btnFilter.setText(item.getName());
    }
}
