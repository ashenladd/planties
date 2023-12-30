package com.example.planties.features.plant_care;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.GardenType;
import com.example.planties.core.SortType;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.domain.garden.model.GardenModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.features.utils.GardenFilter;
import com.example.planties.features.utils.adapter.filter.FilterModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GardenViewModel extends ViewModel {
    private final GardenUseCase gardenUseCase;

    @Inject
    public GardenViewModel(GardenUseCase gardenUseCase) {
        this.gardenUseCase = gardenUseCase;

        getGardens(Objects.requireNonNull(getCurrentFilter().getValue()).getSorting(), getCurrentFilter().getValue().getType());
    }

    private final MutableLiveData<List<GardenModel>> gardenList = new MutableLiveData<>();
    private final MutableLiveData<GardenFilter> currentFilter = new MutableLiveData<>(new GardenFilter(SortType.DESC.getValue(),GardenType.INDOOR.getValue() ));
    public LiveData<GardenFilter> getCurrentFilter() {
        return currentFilter;
    }
    public LiveData<List<GardenModel>> getGardenList() {
        return gardenList;
    }
    public void processEvent(GardenViewEvent event){
        if(event instanceof GardenViewEvent.OnRefresh){
            getGardens(Objects.requireNonNull(currentFilter.getValue()).getSorting(), currentFilter.getValue().getType());
        }else if(event instanceof GardenViewEvent.OnChangedType){
            currentFilter.setValue(new GardenFilter(Objects.requireNonNull(currentFilter.getValue()).getSorting(), ((GardenViewEvent.OnChangedType) event).getType()));
            getGardens(Objects.requireNonNull(getCurrentFilter().getValue()).getSorting(), getCurrentFilter().getValue().getType());
        }else if(event instanceof GardenViewEvent.OnChangedSorting){
            currentFilter.setValue(new GardenFilter(((GardenViewEvent.OnChangedSorting) event).getSorting(), Objects.requireNonNull(currentFilter.getValue()).getType()));
            getGardens(Objects.requireNonNull(getCurrentFilter().getValue()).getSorting(), getCurrentFilter().getValue().getType());
        }
    }

    private void getGardens(String sorting, String type) {
        gardenUseCase.getGardens(sorting, type, new ResponseCallback<List<GardenModel>>() {
            @Override
            public void onSuccess(BaseResultResponse<List<GardenModel>> response) {
                if (response.getData() != null) {
                    gardenList.postValue(response.getData());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<List<GardenModel>> response) {

            }
        });
    }
}
