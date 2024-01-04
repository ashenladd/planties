package com.example.planties.features.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.planties.R;
import com.example.planties.databinding.FragmentAdminBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdminFragment extends Fragment {
    private FragmentAdminBinding binding;
    private AdminViewModel adminViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);

        observeState();
        setupClickListener();
        setupSwipeRefresh();
    }

    private void setupSwipeRefresh() {
        binding.srlApp.setOnRefreshListener(() -> {
            adminViewModel.processEvent(new AdminViewEvent.OnRefresh());
            binding.srlApp.setRefreshing(false);
        });
    }

    private void setupClickListener() {
        binding.btnLogout.setOnClickListener(v -> {
            adminViewModel.processEvent(new AdminViewEvent.OnLogout());
            navigateToLogin();
        });
    }

    private void navigateToLogin() {
        NavDirections directions = AdminFragmentDirections.actionAdminFragmentToLoginFragment();

        NavController navController = Navigation.findNavController(requireView());

        navController.navigate(directions);

    }

    private void observeState() {
        adminViewModel.getAdminLive().observe(getViewLifecycleOwner(), admin -> {
            if (admin != null) {
                setData();
                binding.tvFormatTotalUser.setText(getString(R.string.format_total_user, admin.getTotalUser()));
                binding.tvFormatNewUser.setText(getString(R.string.format_new_user, admin.getTotalNewUser()));
            }
        });
    }

    private void setData() {
        Description desc = new Description();

        Legend l = binding.pcChart.getLegend();
        binding.pcChart.setUsePercentValues(true);
        binding.pcChart.setDrawEntryLabels(false);
        binding.pcChart.getDescription().setEnabled(false);
        binding.pcChart.setExtraOffsets(0, 0, 0, 8);

        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);

        ArrayList<PieEntry> entries = new ArrayList<>();
        if (adminViewModel.getAdminLive().getValue() != null) {
            entries.add(new PieEntry((float) adminViewModel.getAdminLive().getValue().getTanamanDaunHijau(), "Daun Hijau"));
            entries.add(new PieEntry((float) adminViewModel.getAdminLive().getValue().getTanamanBuah(), "Buah"));
            entries.add(new PieEntry((float) adminViewModel.getAdminLive().getValue().getTanamanAir(), "Tanaman Air"));
            entries.add(new PieEntry((float) adminViewModel.getAdminLive().getValue().getTanamanBerbunga(), "Berbunga"));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(requireContext(), R.color.Blue500));
        colors.add(ContextCompat.getColor(requireContext(), R.color.Primary3));
        colors.add(ContextCompat.getColor(requireContext(), R.color.Yellow500));
        colors.add(ContextCompat.getColor(requireContext(), R.color.Red500));

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setValueTextColor(ContextCompat.getColor(requireContext(), R.color.Neutral5));
        dataSet.setValueTextSize(12f);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(binding.pcChart));

        binding.pcChart.setData(data);
        binding.pcChart.invalidate();
    }
}

