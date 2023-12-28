package com.example.planties.features.plant_care.garden.edit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.garden.remote.dto.GardenResDetailDataModel;
import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantResListDataModel;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GardenEditViewModel extends ViewModel {
    private GardenUseCase gardenUseCase;
    private PlantUseCase plantUseCase;

    @Inject
    public GardenEditViewModel(GardenUseCase gardenUseCase,PlantUseCase plantUseCase) {
        this.gardenUseCase = gardenUseCase;
        this.plantUseCase = plantUseCase;
    }

    private MutableLiveData<GardenResModel> gardenDetail = new MutableLiveData<>();

    public MutableLiveData<GardenResModel> getGardenDetail() {
        return gardenDetail;
    }
    private MutableLiveData<PlantResListDataModel> plantList = new MutableLiveData<>();
    public MutableLiveData<PlantResListDataModel> getPlantList() {
        return plantList;
    }

    public void processEvent(GardenEditViewEvent event){
        if (event instanceof GardenEditViewEvent.OnLoadGarden) {
            getDetailGarden(((GardenEditViewEvent.OnLoadGarden) event).gardenId);
            getPlantsWithGardenId(((GardenEditViewEvent.OnLoadGarden) event).gardenId);
        }else if (event instanceof GardenEditViewEvent.OnSaveEdit) {
            if (((GardenEditViewEvent.OnSaveEdit) event).gardenId == null) {
                postGarden(((GardenEditViewEvent.OnSaveEdit) event).gardenReq);
            }else{
                putDetailGarden(((GardenEditViewEvent.OnSaveEdit) event).gardenId,((GardenEditViewEvent.OnSaveEdit) event).gardenReq);
            }
        }
    }

    private void getDetailGarden(String gardenId) {
        gardenUseCase.getDetailGarden(gardenId, new ResponseCallback<GardenDetailRes>() {

            @Override
            public void onSuccess(BaseResultResponse<GardenDetailRes> response) {
                gardenDetail.setValue(response.getData().getData().getGarden());
            }

            @Override
            public void onFailure(BaseResultResponse<GardenDetailRes> response) {

            }
        });
    }
    private void putDetailGarden(String gardenId, GardenReq gardenReq) {
        gardenUseCase.putGarden(gardenId, gardenReq, new ResponseCallback<GardenDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<GardenDetailRes> response) {
                gardenDetail.setValue(response.getData().getData().getGarden());
            }

            @Override
            public void onFailure(BaseResultResponse<GardenDetailRes> response) {

            }
        });
    }

    private void postGarden(GardenReq gardenReq) {
        gardenUseCase.postGarden(gardenReq, new ResponseCallback<GardenDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<GardenDetailRes> response) {
                gardenDetail.setValue(response.getData().getData().getGarden());
            }

            @Override
            public void onFailure(BaseResultResponse<GardenDetailRes> response) {

            }
        });
    }

    private void getPlantsWithGardenId(String gardenId) {
        plantUseCase.getPlantWithGarden(gardenId, new ResponseCallback<PlantListRes>() {

            @Override
            public void onSuccess(BaseResultResponse<PlantListRes> response) {
                plantList.setValue(response.getData().data);
            }

            @Override
            public void onFailure(BaseResultResponse<PlantListRes> response) {

            }
        });
    }
}
