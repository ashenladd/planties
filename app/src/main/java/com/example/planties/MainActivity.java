package com.example.planties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.planties.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent intent = getIntent();
        String destination = intent.getStringExtra("destination");

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        if ("homeFragment2".equals(destination)) {
            // Set starting destination to homeFragment2
            navController.navigate(R.id.homeFragment2);
        } else if ("adminFragment".equals(destination)) {
            // Set starting destination to adminFragment
            navController.navigate(R.id.adminFragment);
        } else {
            // Set starting destination to loginFragment
            navController.navigate(R.id.loginFragment);
        }
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                hideNavBars(navDestination.getId());
            }
        });
    }

    private void hideNavBars(int destinationId) {
        if (destinationId == R.id.homeFragment2 || destinationId == R.id.gardenFragment) {
            binding.bottomNavigationView.setVisibility(View.VISIBLE);
            binding.ivScanFragment.setVisibility(View.VISIBLE);
        } else {
            binding.bottomNavigationView.setVisibility(View.GONE);
            binding.ivScanFragment.setVisibility(View.GONE);
        }
    }
}