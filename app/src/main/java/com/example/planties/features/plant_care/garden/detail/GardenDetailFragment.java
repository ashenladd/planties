package com.example.planties.features.plant_care.garden.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planties.R;
import com.example.planties.databinding.FragmentGardenDetailBinding;
import com.example.planties.features.plant_care.GardenViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GardenDetailFragment extends Fragment {
    FragmentGardenDetailBinding binding;
    private GardenDetailViewModel gardenDetailViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGardenDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gardenDetailViewModel = new ViewModelProvider(this).get(GardenDetailViewModel.class);
    }
}