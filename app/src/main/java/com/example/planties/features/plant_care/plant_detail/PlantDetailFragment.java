package com.example.planties.features.plant_care.plant_detail;

import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.core.utils.ImageUtils;
import com.example.planties.core.utils.TimeUtils;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.data.plant.remote.dto.PlantReqPut;
import com.example.planties.databinding.FragmentPlantDetailBinding;
import com.example.planties.features.plant_care.plant_detail.adapter.PlantAdapter;
import com.example.planties.features.plant_care.plant_detail.adapter.PlantModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
            plantAdapter = new PlantAdapter(pickMedia);
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
        setupClickListener();
        setupDropdown();
    }

    private void setupDropdown() {
        String[] items = new String[]{"Tanaman Berbunga", "Tanaman Buah", "Tanaman Hias", "Tanaman Air"};

        ListPopupWindow listPopupWindow = new ListPopupWindow(requireContext());
        listPopupWindow.setAnchorView(binding.btnTipeTanaman);

        int maxWidth = calculateMaxWidth(items);
        listPopupWindow.setWidth(maxWidth);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.list_popup_window, items);
        listPopupWindow.setAdapter(adapter);

        // Observe the LiveData for changes
        plantDetailViewModel.getTanamanType().observe(getViewLifecycleOwner(), tanamanType -> {
            if (tanamanType != null) {
                // If TanamanType is not null, set the selected item in the dropdown
                String tanamanTypeValue = tanamanType;
                int position = Arrays.asList(items).indexOf(tanamanTypeValue);
                if (position != -1) {
                    // Set the selected item
                    listPopupWindow.setSelection(position);
                    binding.btnTipeTanaman.setText(tanamanTypeValue);
                } else {
                    listPopupWindow.setSelection(0); // Set to the first item as default
                    binding.btnTipeTanaman.setText(items[0]);
                }
            } else {
                listPopupWindow.setSelection(0); // Set to the first item as default
                binding.btnTipeTanaman.setText(items[0]);
            }
        });
        listPopupWindow.setSelection(0); // Set to the first item as default
        binding.btnTipeTanaman.setText(items[0]);

        listPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnClickDropdownValue(items[position]));
            binding.btnTipeTanaman.setText(items[position]);
            listPopupWindow.setSelection(position);
            listPopupWindow.dismiss();
        });

        listPopupWindow.setOnDismissListener(() -> {
            binding.btnTipeTanaman.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_expand, 0);
        });

        binding.btnTipeTanaman.setOnClickListener(v -> {
            if (listPopupWindow.isShowing()) {
                listPopupWindow.dismiss();
            } else {
                listPopupWindow.show();
                binding.btnTipeTanaman.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_less, 0);
            }
        });
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

                        if (plantId != null) {
                            String PlantName;
                            if (!Objects.requireNonNull(binding.tietInput.getText()).toString().isEmpty()) {
                                PlantName = binding.tietInput.getText().toString();
                            } else {
                                PlantName = Objects.requireNonNull(plantDetailViewModel.getPlantDetail().getValue()).getName();
                            }
                            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnAddImage(
                                    gardenId,
                                    plantId,
                                    new PlantReqPut(
                                            PlantName,
                                            PlantName,
                                            listImage)));
                            plantDetailViewModel.getPlantDetail();
                        } else {
                            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnAddPostImage(base64Image));
                        }
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                }
            });

    private void setupSwipeRefresh() {
        binding.srlPlantDetail.setOnRefreshListener(() -> {
            loadPlant();
            binding.srlPlantDetail.setRefreshing(false);
        });

    }

    private void setupClickListener() {
        if (plantId == null) {
            binding.toolbarSave.toolbar.setNavigationOnClickListener(v -> {
                navigateBack();
            });
            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnClickEdit());
            binding.toolbarSave.btnTambahTaman.setOnClickListener(v -> {
                String plantName = Objects.requireNonNull(binding.tietInput.getText()).toString();
                String plantType = Objects.requireNonNull(binding.btnTipeTanaman.getText()).toString();
                if (!plantName.isEmpty()) {
                    plantDetailViewModel.processEvent(
                            new PlantDetailViewEvent.OnAddPlant(
                                    gardenId,
                                    new PlantReq(
                                            plantName,
                                            "Banner " + plantName,
                                            TimeUtils.TimeNow(),
                                            plantType,
                                            plantDetailViewModel.getImageList().getValue())));
                    navigateBack();
                } else {
                    Toast.makeText(requireContext(), "Nama tanaman tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            binding.toolbarSave.toolbar.setNavigationOnClickListener(v -> {
                plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnClickEdit());
            });
            binding.toolbarSave.btnTambahTaman.setOnClickListener(v -> {
                plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnSaveEdit(
                        gardenId,
                        plantId,
                        new PlantReqPut(
                                Objects.requireNonNull(binding.tietInput.getText()).toString(),
                                Objects.requireNonNull(plantDetailViewModel.getPlantDetail().getValue()).getBanner(),
                                null)));
                plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnClickEdit());
            });

        }

        binding.toolbarEdit.ivEdit.setOnClickListener(v -> {
            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnClickEdit());
        });
        binding.toolbarEdit.toolbar.setNavigationOnClickListener(v -> {
            navigateBack();
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
        if (plantId == null) {
            List<PlantModel> plantModelList = new ArrayList<>();
            plantModelList.add(new PlantModel("Add"));
            getPlantAdapter().submitList(plantModelList);
            Log.d("PlantDetailFragment", "ObserveStateImage: " + plantModelList.size());
            binding.clWater.setVisibility(View.GONE);
            binding.tvLabelStatus.setVisibility(View.GONE);
            plantDetailViewModel.getImageList().observe(getViewLifecycleOwner(), imageList -> {
//                Log.d("PlantDetailFragment", "ObserveStateImageList: " + imageList.size());
                List<PlantModel> plantImageList = new ArrayList<>();
                for (String urlImage : imageList) {
                    plantImageList.add(new PlantModel(urlImage));
                }
                plantImageList.add(new PlantModel("Add"));
                getPlantAdapter().submitList(plantImageList);
            });
        }
        plantDetailViewModel.getPlantDetail().observe(getViewLifecycleOwner(), plantDetail -> {
            if (plantDetail != null) {
                List<PlantModel> plantModelList = new ArrayList<>();
                for (String urlImage : plantDetail.getUrlImage()) {
                    plantModelList.add(new PlantModel(urlImage));
                }
                plantModelList.add(new PlantModel("Add"));
                getPlantAdapter().submitList(plantModelList);
                binding.tvPlantName.setText(plantDetail.getName());
                if (!plantDetail.getUrlImage().isEmpty()) {
                    ImageExtensions.loadPlantImage(binding.ivPlant, requireContext(), plantDetail.getUrlImage().get(0));
                }
            }
        });

        plantDetailViewModel.getGardenDetail().observe(getViewLifecycleOwner(), gardenDetail -> {
            binding.btnNamaTaman.setText(gardenDetail.getName());
        });
        plantDetailViewModel.getIsEdit().observe(getViewLifecycleOwner(), isEdit -> {
            if (!isEdit) {
                binding.toolbarEdit.appbar.setVisibility(View.VISIBLE);
                binding.toolbarSave.appbar.setVisibility(View.GONE);
                binding.ivEditName.setVisibility(View.GONE);
                binding.tvPlantName.setVisibility(View.VISIBLE);
                binding.tietInput.setVisibility(View.GONE);
                binding.btnTipeTanaman.setEnabled(false);
                binding.btnTipeTanaman.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            } else {
                binding.toolbarSave.appbar.setVisibility(View.VISIBLE);
                binding.toolbarEdit.appbar.setVisibility(View.GONE);
                binding.ivEditName.setVisibility(View.VISIBLE);
                binding.tvPlantName.setVisibility(View.GONE);
                binding.tietInput.setVisibility(View.VISIBLE);
                binding.tietInput.setText(binding.tvPlantName.getText());
                binding.btnTipeTanaman.setEnabled(true);
                binding.btnTipeTanaman.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_expand, 0);
            }
        });
        plantDetailViewModel.getReminderDetail().observe(getViewLifecycleOwner(), reminderDetail -> {
            int hour = TimeUtils.TimeConvertMinToHour(reminderDetail.getDuration());
            if (reminderDetail.getDuration() <= 0) {
                binding.tvWaterTime.setText(getString(R.string.label_need_water));
                binding.tvWaterTime.setTextColor(ContextCompat.getColor(requireContext(), R.color.Red500));
                setupProgressBar(0);
            } else {
                binding.tvWaterTime.setText(getString(R.string.format_water_time, hour));
                binding.tvWaterTime.setTextColor(ContextCompat.getColor(requireContext(), R.color.Neutral5));
                setupProgressBar(reminderDetail.getDuration());
            }
        });
    }

    private void setupRecyclerView() {
        binding.rvGallery.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvGallery.setAdapter(getPlantAdapter());
    }

    private void loadPlant() {
        if (plantId != null) {
            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnLoadPlant(gardenId, plantId));
        } else {
            plantDetailViewModel.processEvent(new PlantDetailViewEvent.OnLoadGarden(gardenId));
        }
    }

    private void setupProgressBar(int minute) {
        int durationInMillis = minute * 60 * 1000;

        Log.d("PlantDetailFragment", "setupProgressBar: " + durationInMillis);

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(binding.pbWater, "progress", 100, 0);

        progressAnimator.setDuration(durationInMillis);

        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        progressAnimator.start();
    }

    private int calculateMaxWidth(String[] items) {
        int maxWidth = 0;
        for (String item : items) {
            int textWidth = (int) binding.btnTipeTanaman.getPaint().measureText(item);
            maxWidth = Math.max(maxWidth, textWidth);
        }
        return maxWidth + 120;
    }
}

