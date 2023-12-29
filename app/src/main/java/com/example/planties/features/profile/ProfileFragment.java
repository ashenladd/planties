package com.example.planties.features.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planties.databinding.FragmentProfileBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtain the context
        context = getContext();

        // Check if context is not null before initializing the ViewModel
        if (context != null) {
            profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
            setClickListener();
        } else {
            // Handle the case when context is null, e.g., show an error message
            Toast.makeText(requireContext(), "Error: Context is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void setClickListener() {
        binding.btnLogout.setOnClickListener(view1 -> {

        });
    }
}