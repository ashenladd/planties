package com.example.planties.features.auth.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planties.data.source.remote.dto.AuthRequest;
import com.example.planties.databinding.FragmentLoginBinding;


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


        binding.btnLogin.setOnClickListener(view1 -> {
            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername("akifbelanda");
            authRequest.setPassword("12345678");

            loginViewModel.login(authRequest);
        });
    }
}