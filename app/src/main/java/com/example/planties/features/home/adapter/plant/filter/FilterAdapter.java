package com.example.planties.features.home.adapter.plant.filter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.R;
import com.example.planties.databinding.ItemTabFilterBinding;

public class FilterAdapter extends ListAdapter<FilterModel, FilterViewHolder> {
    private int selectedItemPos = 0;
    private int lastItemSelectedPos = 0;
    private FilterListener filterListener;

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
        holder.bind(getItem(position));

        holder.itemView.setOnClickListener(v -> {
            selectedItemPos = holder.getBindingAdapterPosition();

            if (lastItemSelectedPos != -1) {
                notifyItemChanged(lastItemSelectedPos);
            }
            lastItemSelectedPos = selectedItemPos;

            notifyItemChanged(selectedItemPos);

            peformAction(getItem(position));
        });
        if (position == selectedItemPos) {
            holder.itemView.setBackgroundResource(R.drawable.bg_button_primary);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.bg_button_netral1_active);
        }
    }

    private void peformAction(FilterModel item) {
        if (filterListener != null) {
            filterListener.onItemClick(item);
        }

        notifyDataSetChanged();
    }
}
