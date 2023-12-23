package com.example.planties.features.plant_care.plant_detail;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.planties.R;
import com.example.planties.databinding.FragmentHomeBinding;
import com.example.planties.databinding.FragmentPlantDetailBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlantDetailFragment extends Fragment {
    private FragmentPlantDetailBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(binding.pbWater, "progress", 0, 100);

        progressAnimator.setDuration(2000);

        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        progressAnimator.start();

    }
}