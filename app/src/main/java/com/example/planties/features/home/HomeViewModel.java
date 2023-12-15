package com.example.planties.features.home;

import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.domain.garden.model.GardenModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GardenUseCase gardenUseCase;

    @Inject
    public HomeViewModel(GardenUseCase gardenUseCase) {
        this.gardenUseCase = gardenUseCase;
    }

    private void getGarden() {
        gardenUseCase.getGardens(new ResponseCallback<List<GardenModel>>() {
            @Override
            public void onSuccess(BaseResultResponse<List<GardenModel>> response) {
                if(response.getData() != null){
                    for(GardenModel gardenModel : response.getData()){
                        System.out.println(gardenModel.getName());
                    }
                }
            }

            @Override
            public void onFailure(BaseResultResponse<List<GardenModel>> response) {

            }
        });
    }


    private void getDetailGarden(String id){
        gardenUseCase.getDetailGarden(id, new ResponseCallback<GardenDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<GardenDetailRes> response) {
                if(response.getData() != null){
                    System.out.println(response.getData().getData().getName());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<GardenDetailRes> response) {

            }
        });
    }

}
