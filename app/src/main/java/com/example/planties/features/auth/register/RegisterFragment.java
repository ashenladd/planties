package com.example.planties.features.auth.register;

import android.content.Context;
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

import com.example.planties.databinding.FragmentRegisterBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtain the context
        context = getContext();

        // Check if context is not null before initializing the ViewModel
        if (context != null) {
            registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
            setClickListener();
        } else {
            // Handle the case when context is null, e.g., show an error message
            Toast.makeText(requireContext(), "Error: Context is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void setClickListener() {
        binding.btnRegister.setOnClickListener(view1 -> {
            String name = Objects.requireNonNull(binding.layoutInputName.tietName.getText()).toString();
            String username = Objects.requireNonNull(binding.layoutInputEmail.tietUsername.getText()).toString();
            String password = Objects.requireNonNull(binding.layoutInputPassword.tietPassword.getText()).toString();
            String confirmPassword = Objects.requireNonNull(binding.layoutInputConfirmPassword.tietConfirmPassword.getText()).toString();

            if (!name.isEmpty() && !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                if (password.equals(confirmPassword)) {
                    registerViewModel.processEvent(new RegisterViewEvent.RegisterButtonClicked(name, username, password));
                } else {
                    // Show an alert or toast indicating that passwords do not match
                    showToast("Passwords do not match");
                }
            } else {
                // Show an alert or toast indicating that all fields must be filled
                showToast("All fields must be filled");
            }
        });

        binding.tvLabelDonTHaveAnAccountRegister.setOnClickListener(view1 -> {
            navigateToLogin();
        });
    }

    private void navigateToLogin() {
        NavDirections directions = RegisterFragmentDirections.actionRegisterFragment2ToLoginFragment();

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

}
