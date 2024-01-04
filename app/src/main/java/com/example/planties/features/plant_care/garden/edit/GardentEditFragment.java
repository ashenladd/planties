package com.example.planties.features.plant_care.garden.edit;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.planties.core.enum_type.GardenType;
import com.example.planties.core.utils.ImageUtils;
import com.example.planties.core.utils.TimeUtils;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.data.reminder.remote.dto.ReminderReq;
import com.example.planties.databinding.FragmentGardentEditBinding;
import com.example.planties.features.plant_care.garden.edit.adapter.garden.GardenAdapter;
import com.example.planties.features.plant_care.garden.edit.adapter.garden.GardenGalleryModel;
import com.example.planties.features.plant_care.garden.edit.adapter.plant.PlantAdapter;

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
            gardenAdapter = new GardenAdapter(pickMedia);
        }
        return gardenAdapter;
    }

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        String base64Image = ImageUtils.convertImageUriToBase64(requireContext(), uri);
                        List<String> listImage = new ArrayList<>();
                        listImage.add(base64Image);

                        if (gardenId != null) {
                            gardenEditViewModel.processEvent(new GardenEditViewEvent.OnAddImage(
                                    gardenId,
                                    new GardenReq(
                                            null,
                                            null,
                                            listImage)));
                            loadGarden();
                        } else {
                            gardenEditViewModel.processEvent(new GardenEditViewEvent.OnAddPostImage(base64Image));
                        }
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
        observeState();
        loadGarden();
        setupSwipeRefresh();
        setupClickListener();
    }

    private void setupSwipeRefresh() {
        binding.srlGardenEdit.setOnRefreshListener(() -> {
            loadGarden();
            binding.srlGardenEdit.setRefreshing(false);
        });
    }

    private void setupClickListener() {
        if (gardenId == null){
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

            binding.tbGardenEdit.btnTambahTaman.setOnClickListener(v -> {
                String gardenName = Objects.requireNonNull(binding.tietInput.getText()).toString();
                String gardenTypeInput = binding.btnTipeTaman.getText().toString().toUpperCase();
                if (!gardenName.isEmpty()) {
                    Log.d("GardenEditFragment", "setupClickListener: " + gardenEditViewModel.getImageList().getValue());
                    GardenReq gardenReq = new GardenReq(gardenName, gardenTypeInput, gardenEditViewModel.getImageList().getValue());
                    gardenEditViewModel.processEvent(new GardenEditViewEvent.OnAddGarden(gardenReq));
                    loadGarden();
                    Toast.makeText(requireContext(), "Taman Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Nama Taman Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            binding.tbGardenEdit.btnTambahTaman.setOnClickListener(v -> {
                String gardenName = Objects.requireNonNull(binding.tietInput.getText()).toString();
                String gardenType = binding.btnTipeTaman.getText().toString().toUpperCase();
                List<String> imageUrl = new ArrayList<>();
                GardenReq gardenReq = new GardenReq(gardenName, gardenType, imageUrl);
                if (!gardenName.isEmpty()) {
                    gardenEditViewModel.processEvent(new GardenEditViewEvent.OnSaveEdit(gardenId, gardenReq));
                    navigateBack();
                } else {
                    Toast.makeText(requireContext(), "Nama Taman Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void navigateToPlantDetail(String gardenId) {
        NavDirections directions = GardentEditFragmentDirections.actionGardentEditFragmentToPlantDetailFragment(gardenId, null, true);
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

    private void observeState() {
        if (gardenId == null) {
            List<GardenGalleryModel> gardenPhotosModel = new ArrayList<>();
            gardenPhotosModel.add(new GardenGalleryModel("Add"));
            getGardenAdapter().submitList(gardenPhotosModel);

            gardenEditViewModel.getImageList().observe(getViewLifecycleOwner(), imageList -> {
                if (imageList != null) {
                    List<GardenGalleryModel> gardenImageList = new ArrayList<>();
                    for (String urlImage : imageList) {
                        gardenImageList.add(new GardenGalleryModel(urlImage));
                    }
                    gardenImageList.add(new GardenGalleryModel("Add"));
                    getGardenAdapter().submitList(gardenImageList);
                }
            });

            List<PlantResModel> plantModels = new ArrayList<>();
            plantModels.add(new PlantResModel("Add", null, null, null, gardenId, null, null,null));
            getPlantAdapter().submitList(plantModels);

            binding.tvLabelTanaman.setVisibility(View.GONE);
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

                // Check if a reminder is not set, then add a default reminder
                if (gardenEditViewModel.getReminder().getValue() == null) {
                    // Ensure the gardenDetail and its ID are not null
                    if (gardenDetail.getId() != null) {
                        ReminderReq reminderReq = new ReminderReq("Reminder " + gardenDetail.getName(), "watering", TimeUtils.TimeRandom60to180());
                        gardenEditViewModel.processEvent(new GardenEditViewEvent.OnAddReminder(
                                gardenDetail.getId(),
                                reminderReq));
                        Log.d("GardenEditFragment", "PostReminder Success: ");
                    } else {
                        Log.e("GardenEditFragment", "Garden ID is null");
                    }
                }
            }
        });
        if (gardenId != null) {
            gardenEditViewModel.getPlantList().observe(getViewLifecycleOwner(), plantModels -> {
                if (plantModels != null) {
                    List<PlantResModel> plantModelList = new ArrayList<>(plantModels.getPlants());
                    plantModelList.add(new PlantResModel("Add", null, null, null, gardenId, null, null,null));
                    getPlantAdapter().submitList(plantModelList);
                }
            });
        }

    }

    private void setupRecyclerView() {
        if (gardenId != null) {
            binding.rvPlant.setLayoutManager(new GridLayoutManager(requireContext(), 3));
            binding.rvPlant.setAdapter(getPlantAdapter());
        }

        binding.rvGalery.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvGalery.setAdapter(getGardenAdapter());
    }

    private void loadGarden() {
        if (gardenId != null) {
            gardenEditViewModel.processEvent(new GardenEditViewEvent.OnLoadReminder(gardenId));
            gardenEditViewModel.processEvent(new GardenEditViewEvent.OnLoadGarden(gardenId));
        }
    }
}