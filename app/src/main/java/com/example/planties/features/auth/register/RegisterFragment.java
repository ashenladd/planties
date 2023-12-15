package com.example.planties.features.auth.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.planties.databinding.FragmentRegisterBinding;
import com.example.planties.features.auth.login.LoginFragmentDirections;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setClickListener();
    }

    private void setClickListener() {
        binding.tvLabelDonTHaveAnAccountRegister.setOnClickListener(view1 -> {
            navigateToLogin();
        });
    }

    private void navigateToLogin() {
        NavDirections directions = RegisterFragmentDirections.actionRegisterFragment2ToLoginFragment();

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);
    }
}