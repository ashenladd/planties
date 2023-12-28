package com.example.planties.features.home;

import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.data.user.remote.dto.UserResDetailDataModel;
import com.example.planties.databinding.FragmentHomeBinding;
import com.example.planties.features.home.adapter.garden.GardenAdapter;
import com.example.planties.features.home.adapter.plant.PlantAdapter;
import com.example.planties.features.utils.adapter.filter.FilterAdapter;
import com.example.planties.features.utils.adapter.filter.FilterModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private GardenAdapter mGardenAdapter;
    private PlantAdapter mPlantAdapter;
    private FilterAdapter mFilterAdapter;

    private GardenAdapter getGardenAdapter() {
        if (mGardenAdapter == null) {
            mGardenAdapter = new GardenAdapter(this::navigateToGardenDetail);
        }
        return mGardenAdapter;
    }

    private PlantAdapter getPlantAdapter() {
        if (mPlantAdapter == null) {
            mPlantAdapter = new PlantAdapter(this::navigateToPlantDetail);
        }
        return mPlantAdapter;
    }

    private FilterAdapter getFilterAdapter() {
        if (mFilterAdapter == null) {
            mFilterAdapter = new FilterAdapter(item -> {
                Log.d("HomeFragment", "getFilterAdapter: " + item.getName());
                homeViewModel.processEvent(new HomeViewEvent.OnChangedFilter(item));

            });
        }else {
            mFilterAdapter.notifyDataSetChanged();
        }
        return mFilterAdapter;
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

        binding.rvTanaman.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvTanaman.setAdapter(getPlantAdapter());

        binding.rvFilter.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvFilter.setAdapter(getFilterAdapter());
    }

    private void setupSwipeListener() {
        binding.srlApp.setOnRefreshListener(() -> {
            homeViewModel.processEvent(new HomeViewEvent.OnRefresh());
            binding.srlApp.setRefreshing(false);
        });
    }

    private void observeState() {
        homeViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            UserResDetailDataModel profile = user.data;
            ImageExtensions.loadProfileImage(binding.sivProfile, requireContext(), profile.user.urlImage);
            binding.tvUsername.setText(profile.user.fullName);

        });
        homeViewModel.getGardenList().observe(getViewLifecycleOwner(), gardenModels -> {
            int size = gardenModels.size();
            getGardenAdapter().submitList(gardenModels);
            binding.tvFormatBanyakTaman.setText(String.format(getString(R.string.format_kamu_memiliki_taman), size));
        });
        homeViewModel.getPlantList().observe(getViewLifecycleOwner(), plantModels -> {
            List<PlantResModel> plant = new ArrayList<>(plantModels.getPlants());
            getPlantAdapter().submitList(plant);
        });
        homeViewModel.getPlantFilterList().observe(getViewLifecycleOwner(), filterModels -> {
            getFilterAdapter().submitList(filterModels);
        });
        homeViewModel.getSelectedFilter().observe(getViewLifecycleOwner(), filterId -> {
            Log.d("HomeFragment", "observeStateHome: " + filterId);
            List<FilterModel> filterModels = homeViewModel.getPlantFilterList().getValue();
            if (filterModels != null) {
                for (FilterModel filterModel : filterModels) {
                    filterModel.setSelected(Objects.equals(filterId, filterModel.getId()));
                    Log.d("HomeFragment", "observeStateHome: " + filterModel.getName() + " " + filterModel.isSelected());
                }
                getFilterAdapter().submitList(filterModels);
            }
            for (FilterModel filterModel : getFilterAdapter().getCurrentList()) {
                Log.d("HomeFragment", "observeStateHomeCurrent: " + filterModel.getName() + " " + filterModel.isSelected());
            }
        });
    }

    private void navigateToGardenDetail(String id) {
        Log.d("HomeFragment", "navigateToGardenDetail: " + id);
        NavDirections directions = HomeFragmentDirections.actionHomeFragment2ToGardenDetailFragment(id);

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);
    }

    private void navigateToPlantDetail(String gardenId, String plantId) {
        Log.d("HomeFragment", "navigateToPlantDetail: " + gardenId + " " + plantId);
        NavDirections directions = HomeFragmentDirections.actionHomeFragment2ToPlantDetailFragment(gardenId, plantId, false);

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);
    }

}