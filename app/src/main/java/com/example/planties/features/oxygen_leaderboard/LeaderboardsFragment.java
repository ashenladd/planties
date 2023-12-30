package com.example.planties.features.oxygen_leaderboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planties.R;
import com.example.planties.databinding.FragmentLeaderboardsBinding;
import com.example.planties.features.oxygen_leaderboard.adapter.LeaderboardsAdapter;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LeaderboardsFragment extends Fragment {
    FragmentLeaderboardsBinding binding;
    private LeaderboardsViewModel leaderboardsViewModel;

    private LeaderboardsAdapter mLeaderboardsAdapter;

    private LeaderboardsAdapter getLeaderboardsAdapter() {
        if (mLeaderboardsAdapter == null) {
            mLeaderboardsAdapter = new LeaderboardsAdapter();
        }
        return mLeaderboardsAdapter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLeaderboardsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        leaderboardsViewModel = new ViewModelProvider(this).get(LeaderboardsViewModel.class);
        setupRecyclerView();
        observeState();
        setupBackPressed();
        swipeRefreshLayout();
        setupToolbar();
    }

    private void setupToolbar() {
        binding.tbLeaderboards.tvTitle.setText(getString(R.string.label_leaderboard));
        binding.tbLeaderboards.toolbar.setNavigationOnClickListener(v -> navigateBack());
    }

    private void setupBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigateBack();
            }
        });
    }

    private void navigateBack() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigateUp();
    }

    private void swipeRefreshLayout() {
        binding.srlLeaderboards.setOnRefreshListener(() -> {
            leaderboardsViewModel.processEvent(new LeaderboardsViewEvent.OnRefresh());
            binding.srlLeaderboards.setRefreshing(false);
        });
    }

    private void observeState() {
        leaderboardsViewModel.getLeaderboards().observe(getViewLifecycleOwner(), leaderboards -> {
            if (leaderboards != null) {
                getLeaderboardsAdapter().submitList(leaderboards);
            }
        });
        leaderboardsViewModel.getUserLeaderboard().observe(getViewLifecycleOwner(), userLeaderboard -> {
            Log.d("LeaderboardsFragment", "observeState: " + userLeaderboard);
            if (userLeaderboard != null) {
                String formattedOxygen = String.format(Locale.getDefault(), "%.2f", userLeaderboard.getOxygen());
                binding.tvFormatPeringkat.setText(getString(R.string.format_peringkat, userLeaderboard.getRank()));
                binding.tvDescDetailOxygen.setText(getString(R.string.format_menghasilkan_oksigen, formattedOxygen));
            }
        });
    }

    private void setupRecyclerView() {
        binding.rvLeaderboards.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvLeaderboards.setAdapter(getLeaderboardsAdapter());
    }
}