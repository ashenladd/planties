package com.example.planties.features.plant_care.garden.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planties.databinding.FragmentGardenDetailBinding;
import com.example.planties.features.plant_care.garden.detail.adapter.garden.GardenAdapter;
import com.example.planties.features.plant_care.garden.detail.adapter.garden.GardenPhotosModel;
import com.example.planties.features.plant_care.garden.detail.adapter.plant.PlantAdapter;
import com.example.planties.features.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GardenDetailFragment extends Fragment {
    FragmentGardenDetailBinding binding;
    private GardenDetailViewModel gardenDetailViewModel;
    private GardenAdapter gardenAdapter;
    private PlantAdapter plantAdapter;
    private String gardenId;

    private GardenAdapter getGardenAdapter() {
        if (gardenAdapter == null) {
            gardenAdapter = new GardenAdapter();
        }
        return gardenAdapter;
    }

    private PlantAdapter getPlantAdapter() {
        if (plantAdapter == null) {
            plantAdapter = new PlantAdapter(this::navigateToPlantDetail);
        }
        return plantAdapter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGardenDetailBinding.inflate(inflater, container, false);
        gardenId = GardenDetailFragmentArgs.fromBundle(getArguments()).getId();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gardenDetailViewModel = new ViewModelProvider(this).get(GardenDetailViewModel.class);

        setupBackPressed();
        setupRecyclerView();
        observeState();
        setupToolbar();
        loadGarden();
        setupSwipeRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadGarden();
    }

    private void setupToolbar() {
        binding.tbGardenDetail.toolbar.setNavigationOnClickListener(v -> navigateBack());
        binding.tbGardenDetail.ivEdit.setOnClickListener(v -> navigateToEditGarden(gardenId));
    }

    private void navigateToEditGarden(String id) {
        NavDirections directions = GardenDetailFragmentDirections.actionGardenDetailFragmentToGardentEditFragment(id);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(directions);
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

    private void setupSwipeRefresh() {
        binding.srlGarden.setOnRefreshListener(() -> {
            loadGarden();
            binding.srlGarden.setRefreshing(false);
        });
    }

    private void loadGarden() {
        gardenDetailViewModel.processEvent(new GardenDetailViewEvent.OnLoadGarden(gardenId));
        gardenDetailViewModel.processEvent(new GardenDetailViewEvent.OnLoadPlants(gardenId));
    }

    private void observeState() {
        gardenDetailViewModel.getGardenDetail().observe(getViewLifecycleOwner(), gardenDetail -> {
            binding.tbGardenDetail.tvTitle.setText(gardenDetail.getName());
            List<GardenPhotosModel> gardenPhotosModel = new ArrayList<>();
            for (String urlImage : gardenDetail.getUrlImage()) {
                GardenPhotosModel photosModel = new GardenPhotosModel(urlImage);
                gardenPhotosModel.add(photosModel);
            }
            getGardenAdapter().submitList(gardenPhotosModel);
        });
        gardenDetailViewModel.getPlantList().observe(getViewLifecycleOwner(), plantList -> {
            Log.d("GardenDetailFragment", "observeState: " + plantList.data.getPlants().size());
            getPlantAdapter().submitList(plantList.data.getPlants());
        });
    }

    private void setupRecyclerView() {
        binding.rvPhotos.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvPhotos.addItemDecoration(new SpaceItemDecoration(48,false,false,false,false,0));
        binding.rvPhotos.setAdapter(getGardenAdapter());

        binding.rvPlant.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvPlant.setAdapter(getPlantAdapter());
    }

    private void navigateToPlantDetail(String gardenId, String plantId, boolean isAddPlant) {
        NavDirections directions = GardenDetailFragmentDirections.actionGardenDetailFragmentToPlantDetailFragment(gardenId,plantId,isAddPlant);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(directions);
    }
}