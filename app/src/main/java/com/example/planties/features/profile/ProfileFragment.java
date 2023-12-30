package com.example.planties.features.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planties.R;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.databinding.FragmentProfileBinding;
import com.example.planties.features.auth.login.LoginFragmentDirections;

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
        observeState();
        setupBackPressed();
        setupToolbar();
    }

    private void setupToolbar() {
        binding.tbProfile.tvTitle.setText(getString(R.string.label_profile));
        binding.tbProfile.toolbar.setNavigationOnClickListener(v -> navigateBack());
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
        profileViewModel.getUser().observe(getViewLifecycleOwner(), text -> {
            binding.descriptionTextView.setText("Loading..");
            binding.descriptionGarden.setText("Loading..");
            binding.rankDescription.setText("Loading..");
            if(text != null) {
                binding.descriptionTextView.setText(String.valueOf(text.total_plant));
                ImageExtensions.loadProfileImage(binding.sivProfile, requireContext(), text.urlImage);
                binding.descriptionGarden.setText(String.valueOf(text.total_garden));
                binding.rankDescription.setText(String.valueOf(text.rank));
            }
        });
    }

    private void navigateToLogin() {
        NavDirections directions = ProfileFragmentDirections.actionProfileFragmentToLoginFragment();

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);
    }


    private void setClickListener() {
        binding.btnLogout.setOnClickListener(view1 -> {
            profileViewModel.logout();
            navigateToLogin();
        });
    }
}