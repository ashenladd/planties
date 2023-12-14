package com.example.planties.features.auth.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planties.data.auth.remote.dto.AuthRequest;
import com.example.planties.databinding.FragmentLoginBinding;


import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    private FragmentLoginBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        setupObserver();
        setupClickListener();
    }

    private void setupClickListener() {
        binding.btnLogin.setOnClickListener(view1 -> {
            String username = Objects.requireNonNull(binding.layoutInputUsername.tietUsername.getText()).toString();
            String password = Objects.requireNonNull(binding.layoutInputPassword.tietPassword.getText()).toString();

            loginViewModel.processEvent(new LoginViewEvent.LoginButtonClicked(username, password));
        });
        binding.tvLabelAlreadyHaveAccountRegister.setOnClickListener(view1 -> {
            navigateToRegister();
        });
    }

    private void navigateToRegister() {
        NavDirections directions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2();

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);
    }

    private void setupObserver() {
        loginViewModel.getAuthResponseLiveData().observe(getViewLifecycleOwner(), response -> {
            if (response.isSuccess()) {
                // Handle successful authentication
                showToast("Authentication successful");
            } else {
                // Handle authentication failure
                showToast("Authentication failed. Error: " + response.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}