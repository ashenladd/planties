package com.example.planties.features.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.user.remote.dto.UserResDetailDataModel;
import com.example.planties.databinding.FragmentHomeBinding;
import com.example.planties.features.home.adapter.garden.GardenAdapter;
import com.example.planties.features.home.adapter.plant.filter.FilterAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private GardenAdapter mGardenAdapter;
    private ConcatAdapter mPlantAdapter;

    private GardenAdapter getGardenAdapter() {
        if (mGardenAdapter == null) {
            mGardenAdapter = new GardenAdapter(this::navigateToGardenDetail);
        }
        return mGardenAdapter;
    }

    private ConcatAdapter getPlantAdapter() {
        if (mPlantAdapter == null) {
            mPlantAdapter = new ConcatAdapter(
                    new FilterAdapter(item -> {
                        homeViewModel.processEvent(new HomeViewEvent.OnChangedFilter(item));
                    })
            );
        }
        return mPlantAdapter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        setupRecyclerView();
        observeState();
        setupSwipeListener();
    }

    private void setupRecyclerView() {
        binding.rvTaman.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvTaman.setAdapter(getGardenAdapter());


    }

    private void setupSwipeListener() {
        binding.swApp.setOnRefreshListener(() -> {
            homeViewModel.processEvent(new HomeViewEvent.OnRefresh());
            binding.swApp.setRefreshing(false);
        });
    }

    private void observeState() {
        homeViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                UserResDetailDataModel profile = user.data;
                ImageExtensions.loadProfileImage(binding.sivProfile, requireContext(), profile.user.urlImage);
                binding.tvUsername.setText(profile.user.fullName);
            }
        });
        homeViewModel.getGardenList().observe(getViewLifecycleOwner(), gardenModels -> {
            if (gardenModels != null) {
                getGardenAdapter().submitList(gardenModels);
                binding.tvFormatBanyakTaman.setText(String.format(getString(R.string.format_kamu_memiliki_taman), gardenModels.size()));
            }
        });
    }

    private void navigateToGardenDetail(String id) {
        NavDirections directions = HomeFragmentDirections.actionHomeFragment2ToGardenDetailFragment(id);

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);
    }
}