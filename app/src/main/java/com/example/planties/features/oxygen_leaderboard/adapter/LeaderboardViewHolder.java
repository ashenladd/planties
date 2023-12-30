package com.example.planties.features.oxygen_leaderboard.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planties.R;
import com.example.planties.data.leaderboards.remote.dto.LeaderboardsResModel;
import com.example.planties.databinding.ItemLeaderboardBinding;

import java.util.Locale;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder{
    private final ItemLeaderboardBinding binding;

    public LeaderboardViewHolder(ItemLeaderboardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(LeaderboardsResModel item) {
        if(item.getRank() == 1){
            binding.clLeaderboard.setBackgroundResource(R.drawable.bg_container_leaderboard1);
        } else if(item.getRank() == 2){
            binding.clLeaderboard.setBackgroundResource(R.drawable.bg_container_leaderboard2);
        } else if(item.getRank() == 3){
            binding.clLeaderboard.setBackgroundResource(R.drawable.bg_container_leaderboard3);
        } else {
            binding.clLeaderboard.setBackgroundResource(R.drawable.bg_container_backgroundgrey_active);
        }
        binding.tvUser.setText(item.getUsername());
        binding.tvOxygen.setText(String.format(Locale.getDefault(),"%.2f", item.getOxygen()));
        binding.tvRank.setText(String.valueOf(item.getRank()));
    }
}
