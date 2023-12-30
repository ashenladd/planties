package com.example.planties.features.plant_care;

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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planties.core.enum_type.GardenType;
import com.example.planties.databinding.FragmentGardenBinding;
import com.example.planties.features.home.adapter.garden.GardenAdapter;
import com.example.planties.features.utils.adapter.filter.FilterAdapter;
import com.example.planties.features.utils.adapter.filter.FilterModel;

import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GardenFragment extends Fragment {
    FragmentGardenBinding binding;
    private GardenViewModel gardenViewModel;
    private GardenAdapter mGardenAdapter;
    private FilterAdapter mFilterAdapter;

    private GardenAdapter getGardenAdapter() {
        if (mGardenAdapter == null) {
            mGardenAdapter = new GardenAdapter(this::navigateToGardenDetail);
        }
        return mGardenAdapter;
    }

    private FilterAdapter getFilterAdapter() {
        if (mFilterAdapter == null) {
            mFilterAdapter = new FilterAdapter(item -> {
                Log.d("GardenFragment", "getFilterAdapter: " + item.getName());
                gardenViewModel.processEvent(new GardenViewEvent.OnChangedType(item.getName()));

            });
        }
        return mFilterAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGardenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gardenViewModel = new ViewModelProvider(this).get(GardenViewModel.class);

        setupRecyclerView();
        observeState();
        setupSwipeListener();
    }


    private void setupSwipeListener() {
        binding.srlGarden.setOnRefreshListener(() -> {
            gardenViewModel.processEvent(new GardenViewEvent.OnRefresh());
            binding.srlGarden.setRefreshing(false);
        });
    }

    private void observeState() {
        gardenViewModel.getGardenList().observe(getViewLifecycleOwner(), gardenList -> {
            getGardenAdapter().submitList(gardenList);
        });
        gardenViewModel.getCurrentFilter().observe(getViewLifecycleOwner(), filter -> {
            List<FilterModel> typeList = Arrays.asList(
                    new FilterModel(GardenType.INDOOR.getValue(), GardenType.INDOOR.getValue(),filter.getType().equals(GardenType.INDOOR.getValue())),
                    new FilterModel(GardenType.OUTDOOR.getValue(), GardenType.OUTDOOR.getValue(),filter.getType().equals(GardenType.OUTDOOR.getValue()))
            );
            getFilterAdapter().submitList(typeList);
        });
    }

    private void setupRecyclerView() {
        binding.rvGarden.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvGarden.setAdapter(getGardenAdapter());

        binding.filter.rvFilter.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.filter.rvFilter.setAdapter(getFilterAdapter());
    }

    private void navigateToGardenDetail(String id) {
        NavDirections directions = GardenFragmentDirections.actionGardenFragmentToGardenDetailFragment(id);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(directions);
    }


    private void navigateBack() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigateUp();
    }
}