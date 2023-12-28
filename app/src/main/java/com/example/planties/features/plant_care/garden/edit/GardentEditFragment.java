package com.example.planties.features.plant_care.garden.edit;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.example.planties.R;
import com.example.planties.core.GardenType;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.databinding.FragmentGardentEditBinding;
import com.example.planties.features.plant_care.GardenFragmentDirections;
import com.example.planties.features.plant_care.garden.edit.adapter.garden.GardenAdapter;
import com.example.planties.features.plant_care.garden.edit.adapter.garden.GardenGalleryModel;
import com.example.planties.features.plant_care.garden.edit.adapter.plant.PlantAdapter;
import com.example.planties.features.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GardentEditFragment extends Fragment {
    FragmentGardentEditBinding binding;
    private GardenEditViewModel gardenEditViewModel;
    private PlantAdapter plantAdapter;

    private String gardenId;

    private PlantAdapter getPlantAdapter() {
        if (plantAdapter == null) {
            plantAdapter = new PlantAdapter(this::navigateToPlantDetail);
        }
        return plantAdapter;
    }

    private GardenAdapter gardenAdapter;

    private GardenAdapter getGardenAdapter() {
        if (gardenAdapter == null) {
            gardenAdapter = new GardenAdapter();
        }
        return gardenAdapter;
    }

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGardentEditBinding.inflate(inflater, container, false);
        gardenId = GardentEditFragmentArgs.fromBundle(getArguments()).getId();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gardenEditViewModel = new ViewModelProvider(this).get(GardenEditViewModel.class);

        setupRecyclerView();
        setupBackPressed();
        setupToolbar();
        obeserveState();
        loadGarden();
        setupClickListener();
    }

    private void setupClickListener() {
        binding.tbGardenEdit.btnTambahTaman.setOnClickListener(v -> {
            String gardenName = Objects.requireNonNull(binding.tietInput.getText()).toString();
            String gardenType = binding.btnTipeTaman.getText().toString().toUpperCase();
            List<String> imageUrl = new ArrayList<>();
            GardenReq gardenReq = new GardenReq(gardenName, gardenType, imageUrl);
            gardenEditViewModel.processEvent(new GardenEditViewEvent.OnSaveEdit(gardenId, gardenReq));
            navigateBack();
        });
        if (gardenId == null) {
            List<String> gardenType = Arrays.asList(
                    GardenType.INDOOR.getValue(),
                    GardenType.OUTDOOR.getValue()
            );
            binding.btnTipeTaman.setText(gardenType.get(0));
            binding.btnTipeTaman.setOnClickListener(v -> {
                if (binding.btnTipeTaman.getText().toString().equals(gardenType.get(0))) {
                    binding.btnTipeTaman.setText(gardenType.get(1));
                } else {
                    binding.btnTipeTaman.setText(gardenType.get(0));
                }
            });
        }
    }

    private void navigateToPlantDetail(String gardenId) {
        NavDirections directions = GardentEditFragmentDirections.actionGardentEditFragmentToPlantDetailFragment(gardenId, null,true);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(directions);
    }

    private void setupToolbar() {
        if (gardenId == null) {
            binding.tbGardenEdit.tvTitle.setText(getString(R.string.label_tambah_taman));
        } else {
            binding.tbGardenEdit.tvTitle.setText(getString(R.string.label_edit_taman));
        }

        binding.tbGardenEdit.toolbar.setNavigationOnClickListener(v -> navigateBack());
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

    private void obeserveState() {
        if (gardenId == null) {
            List<GardenGalleryModel> gardenPhotosModel = new ArrayList<>();
            gardenPhotosModel.add(new GardenGalleryModel("Add"));
            getGardenAdapter().submitList(gardenPhotosModel);

            List<PlantResModel> plantModels = new ArrayList<>();
            plantModels.add(new PlantResModel("Add", null, null, null, gardenId, null, null));
            getPlantAdapter().submitList(plantModels);
        }
        gardenEditViewModel.getGardenDetail().observe(getViewLifecycleOwner(), gardenDetail -> {
            if (gardenDetail != null) {
                binding.tietInput.setText(gardenDetail.getName());
                String formattedType = gardenDetail.getType()
                        .substring(0, 1).toUpperCase(Locale.ROOT) +
                        gardenDetail.getType().substring(1).toLowerCase(Locale.ROOT);
                binding.btnTipeTaman.setText(formattedType);
                List<GardenGalleryModel> gardenPhotosModel = new ArrayList<>();
                for (String urlImage : gardenDetail.getUrlImage()) {
                    GardenGalleryModel photosModel = new GardenGalleryModel(urlImage);
                    gardenPhotosModel.add(photosModel);
                }
                gardenPhotosModel.add(new GardenGalleryModel("Add"));
                getGardenAdapter().submitList(gardenPhotosModel);
            }
        });
        gardenEditViewModel.getPlantList().observe(getViewLifecycleOwner(), plantModels -> {
            if (plantModels != null) {
                List<PlantResModel> plantModelList = new ArrayList<>(plantModels.getPlants());
                plantModelList.add(new PlantResModel("Add", null, null, null, gardenId, null, null));
                getPlantAdapter().submitList(plantModelList);
            }
        });
    }

    private void setupRecyclerView() {
        binding.rvPlant.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        binding.rvPlant.setAdapter(getPlantAdapter());

        binding.rvGalery.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvGalery.setAdapter(getGardenAdapter());
    }

    private void loadGarden() {
        if (gardenId != null) {
            gardenEditViewModel.processEvent(new GardenEditViewEvent.OnLoadGarden(gardenId));
        }
    }
}