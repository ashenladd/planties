package com.example.planties.features.oxygen_leaderboard.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.planties.data.leaderboards.remote.dto.LeaderboardsResModel;
import com.example.planties.databinding.ItemLeaderboardBinding;
import com.example.planties.features.home.adapter.garden.GardenDiffUtil;
import com.example.planties.features.home.adapter.garden.GardenListener;

public class LeaderboardsAdapter extends ListAdapter<LeaderboardsResModel, LeaderboardViewHolder> {

    public LeaderboardsAdapter() {
        super(new LeaderboardDiffUtill());
    }
    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLeaderboardBinding binding = ItemLeaderboardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LeaderboardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
