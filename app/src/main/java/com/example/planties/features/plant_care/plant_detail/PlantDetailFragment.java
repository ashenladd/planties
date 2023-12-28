package com.example.planties.features.plant_care.plant_detail;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.databinding.FragmentPlantDetailBinding;
import com.example.planties.features.plant_care.plant_detail.adapter.PlantAdapter;
import com.example.planties.features.plant_care.plant_detail.adapter.PlantModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlantDetailFragment extends Fragment {
    private FragmentPlantDetailBinding binding;
    private PlantDetailViewModel plantDetailViewModel;
    private PlantAdapter plantAdapter;
    private String gardenId;
    private String plantId;
    private boolean isAddPlant;

    private PlantAdapter getPlantAdapter() {
        if (plantAdapter == null) {
            plantAdapter = new PlantAdapter();
        }
        return plantAdapter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false);
        gardenId = PlantDetailFragmentArgs.fromBundle(getArguments()).getGardenId();
        plantId = PlantDetailFragmentArgs.fromBundle(getArguments()).getPlantId();
        isAddPlant = PlantDetailFragmentArgs.fromBundle(getArguments()).getIsAddPlant();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plantDetailViewModel = new ViewModelProvider(this).get(PlantDetailViewModel.class);

        setupRecyclerView();
        ObserveState();
        setupBackPressed();
        loadPlant();
        setupSwipeRefresh();
        setupProgressBar();
        setupClickListener();
    }

    private void setupSwipeRefresh() {
        binding.srlPlantDetail.setOnRefreshListener(() -> {
            loadPlant();
            binding.srlPlantDetail.setRefreshing(false);
        });

    }

    private void setupClickListener() {
        binding.toolbarEdit.ivEdit.setOnClickListener(v -> {
            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnClickEdit());
        });
        binding.toolbarEdit.toolbar.setNavigationOnClickListener(v -> navigateBack());
        binding.toolbarSave.toolbar.setNavigationOnClickListener(v -> {
            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnClickEdit());
        });
        binding.toolbarSave.btnTambahTaman.setOnClickListener(v -> {
            if (isAddPlant) {
                plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnAddPlant(gardenId, new PlantReq("", "", "","",new ArrayList<>())));
            } else {
                plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnSaveEdit(gardenId, plantId, new PlantReq("", "", "","", new ArrayList<>())));
            }
        });
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

    private void ObserveState() {
        if (!isAddPlant) {
            plantDetailViewModel.getPlantDetail().observe(getViewLifecycleOwner(), plantDetail -> {
                List<PlantModel> plantModelList = new ArrayList<>();
                for (String urlImage : plantDetail.getUrlImage()) {
                    plantModelList.add(new PlantModel(urlImage));
                }
                getPlantAdapter().submitList(plantModelList);
                binding.tvPlantName.setText(plantDetail.getName());
                ImageExtensions.loadPlantImage(binding.ivPlant, requireContext() ,plantDetail.getUrlImage().get(0));
            });
        }
        plantDetailViewModel.getGardenDetail().observe(getViewLifecycleOwner(), gardenDetail -> {
            binding.btnNamaTaman.setText(gardenDetail.getName());
        });
        plantDetailViewModel.getIsEdit().observe(getViewLifecycleOwner(), isEdit -> {
            if (!isEdit) {
                binding.toolbarEdit.appbar.setVisibility(View.VISIBLE);
                binding.toolbarSave.appbar.setVisibility(View.GONE);
                binding.ivEditName.setVisibility(View.GONE);
            } else {
                binding.toolbarSave.appbar.setVisibility(View.VISIBLE);
                binding.toolbarEdit.appbar.setVisibility(View.GONE);
                binding.ivEditName.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupRecyclerView() {
        binding.rvGallery.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvGallery.setAdapter(getPlantAdapter());
    }

    private void loadPlant() {
        plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnLoadPlant(gardenId, plantId));
    }

    private void setupProgressBar() {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(binding.pbWater, "progress", 0, 100);

        progressAnimator.setDuration(2000);

        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        progressAnimator.start();
    }
}