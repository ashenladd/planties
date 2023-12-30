package com.example.planties.features.scan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.planties.R;
import com.example.planties.core.utils.ImageUtils;
import com.example.planties.databinding.FragmentScanBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScanFragment extends Fragment {
    private ScanViewModel scanViewModel;
    private FragmentScanBinding binding;
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
    };
    private static final String TAG = "CameraXApp";
    private ImageCapture imageCapture;
    private ExecutorService cameraExecutor;
    private final ActivityResultLauncher<String[]> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                    permissions -> {
                        // Handle Permission granted/rejected
                        boolean permissionGranted = true;
                        for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
                            if (Arrays.asList(REQUIRED_PERMISSIONS).contains(entry.getKey()) && !entry.getValue()) {
                                permissionGranted = false;
                            }
                        }
                        if (!permissionGranted) {
                            Toast.makeText(requireContext(),
                                    "Permission request denied",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startCamera();
                        }
                    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scanViewModel = new ViewModelProvider(this).get(ScanViewModel.class);

        setupBackPressed();
        setupToolbaar();
        setupPermissions();
        observeState();
        setupClickListener();
        cameraExecutor = Executors.newSingleThreadExecutor();
    }

    private void setupBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigateBack();
            }
        });
    }

    private void setupToolbaar() {
        binding.tbScan.tvTitle.setText(R.string.label_scan_plant);
        binding.tbScan.toolbar.setNavigationOnClickListener(v -> {
            navigateBack();
        });
    }

    private void navigateBack() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigateUp();
    }

    private void setupClickListener() {
        binding.btnCapture.setOnClickListener(v -> {
            scanViewModel.processEvent(new ScanViewEvent.OnTakePhoto(requireContext(), imageCapture));
        });
        binding.clDiagnosis.setOnClickListener(v -> {
            binding.clIdentifying.setVisibility(View.VISIBLE);
            binding.tvAction.setText(R.string.label_mendiagnosis_tanamanmu);
            binding.clDiagnosis.setVisibility(View.GONE);

            scanViewModel.processEvent(new ScanViewEvent.OnDiagnose(scanViewModel.getCapturedImagePath().getValue(), requireContext()));
        });
    }

    private void observeState() {
        scanViewModel.getCapturedImagePath().observe(getViewLifecycleOwner(), path -> {
            if (path != null) {
                binding.btnCapture.setVisibility(View.GONE);
                binding.previewView.setVisibility(View.GONE);
                binding.cvPreview.setVisibility(View.VISIBLE);
                binding.tvAction.setVisibility(View.VISIBLE);
                binding.clDiagnosis.setVisibility(View.VISIBLE);
                binding.tvAiConfident.setVisibility(View.VISIBLE);
                ImageUtils.displayImage(requireContext(), path, binding.imgPreview);
            }
        });
        scanViewModel.getScanResult().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                binding.pbIdentifying.setVisibility(View.GONE);
                binding.tvAction.setText(getString(R.string.label_selesai_diagnosis));
                String pred = result.prediction;
                if (pred.contains("healthy") || pred.contains("Healthy")) {
                    binding.tvAiConfident.setVisibility(View.VISIBLE);
                    binding.tvIdentifying.setText(pred);
                    binding.ivMood.setImageResource(R.drawable.ic_smile);
                    binding.ivMood.setVisibility(View.VISIBLE);
                    binding.tvAiConfident.setText(getString(R.string.format_ai_confident, result.confidence));
                } else {
                    binding.tvAiConfident.setVisibility(View.VISIBLE);
                    binding.tvIdentifying.setText(pred);
                    binding.ivMood.setImageResource(R.drawable.ic_sad);
                    binding.ivMood.setVisibility(View.VISIBLE);
                    binding.tvAiConfident.setText(getString(R.string.format_ai_confident, result.confidence));
                }
            }
        });
    }

    private void setupPermissions() {
        if (allPermissionsGranted()) {
            startCamera();
        } else {
            requestPermissions();
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(requireContext());

        cameraProviderFuture.addListener(() -> {
            try {
                // Used to bind the lifecycle of cameras to the lifecycle owner
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Preview
                Preview preview = new Preview.Builder()
                        .build();

                preview.setSurfaceProvider(binding.previewView.getSurfaceProvider());

                imageCapture = new ImageCapture.Builder()
                        .build();

                // Select back camera as a default
                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                // Unbind use cases before rebinding
                cameraProvider.unbindAll();

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

            } catch (Exception exc) {
                Log.e(TAG, "Use case binding failed", exc);
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    private void requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS);
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}