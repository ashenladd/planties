package com.example.planties.features.plant_care.plant_detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.enum_type.TanamanType;
import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.data.plant.remote.dto.PlantReqPut;
import com.example.planties.data.plant.remote.dto.PlantResModel;
import com.example.planties.data.reminder.remote.dto.ReminderListRes;
import com.example.planties.data.reminder.remote.dto.ReminderResModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;
import com.example.planties.domain.reminder.usecase.ReminderUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlantDetailViewModel extends ViewModel {
    private PlantUseCase plantUseCase;
    private GardenUseCase gardenUseCase;
    private ReminderUseCase reminderUseCase;

    @Inject
    public PlantDetailViewModel(PlantUseCase plantUseCase, GardenUseCase gardenUseCase, ReminderUseCase reminderUseCase) {
        this.plantUseCase = plantUseCase;
        this.gardenUseCase = gardenUseCase;
        this.reminderUseCase = reminderUseCase;
    }

    private MutableLiveData<PlantResModel> plantDetail = new MutableLiveData<>();

    public LiveData<PlantResModel> getPlantDetail() {
        return plantDetail;
    }

    private MutableLiveData<GardenResModel> gardenDetail = new MutableLiveData<>();

    public LiveData<GardenResModel> getGardenDetail() {
        return gardenDetail;
    }

    private MutableLiveData<Boolean> isEdit = new MutableLiveData<>(false);

    public LiveData<Boolean> getIsEdit() {
        return isEdit;
    }

    private MutableLiveData<List<String>> imageList = new MutableLiveData<>();

    public LiveData<List<String>> getImageList() {
        return imageList;
    }

    private MutableLiveData<ReminderResModel> reminderDetail = new MutableLiveData<>();

    public LiveData<ReminderResModel> getReminderDetail() {
        return reminderDetail;
    }
    private MutableLiveData<String> tanamanType = new MutableLiveData<>();

    public LiveData<String> getTanamanType() {
        return tanamanType;
    }

    public void processEvent(PlantDetailViewEvent event) {
        if (event instanceof PlantDetailViewEvent.OnSaveEdit) {
            putDetailPlant(((PlantDetailViewEvent.OnSaveEdit) event).getGardenId(),
                    ((PlantDetailViewEvent.OnSaveEdit) event).getPlantId(),
                    ((PlantDetailViewEvent.OnSaveEdit) event).getPlantReq());
        } else if (event instanceof PlantDetailViewEvent.OnLoadPlant) {
            getDetailPlant(((PlantDetailViewEvent.OnLoadPlant) event).getGardenId(),
                    ((PlantDetailViewEvent.OnLoadPlant) event).getPlantId());
            getDetailGarden(((PlantDetailViewEvent.OnLoadPlant) event).getGardenId());
            getReminder(((PlantDetailViewEvent.OnLoadPlant) event).getGardenId());
        } else if (event instanceof PlantDetailViewEvent.OnClickEdit) {
            isEdit.setValue(Boolean.FALSE.equals(isEdit.getValue()));
        } else if (event instanceof PlantDetailViewEvent.OnAddImage) {
            putDetailPlant(((PlantDetailViewEvent.OnAddImage) event).getGardenId(),
                    ((PlantDetailViewEvent.OnAddImage) event).getPlantId(),
                    ((PlantDetailViewEvent.OnAddImage) event).getPlantReq());
        } else if (event instanceof PlantDetailViewEvent.OnLoadGarden) {
            getDetailGarden(((PlantDetailViewEvent.OnLoadGarden) event).getGardenId());
        } else if (event instanceof PlantDetailViewEvent.OnAddPostImage) {
            List<String> list = imageList.getValue();

            if (list == null) {
                list = new ArrayList<>();
            }

            String newImage = ((PlantDetailViewEvent.OnAddPostImage) event).getImage();
            Log.d("PlantDetailViewModel", "processEvent: " + newImage);

            list.add(newImage);

            imageList.postValue(list);
        } else if (event instanceof PlantDetailViewEvent.OnAddPlant) {
            postDetailPlant(((PlantDetailViewEvent.OnAddPlant) event).getGardenId(),
                    ((PlantDetailViewEvent.OnAddPlant) event).getPlantReq());
        } else if (event instanceof PlantDetailViewEvent.OnClickDropdownValue){
            tanamanType.postValue(((PlantDetailViewEvent.OnClickDropdownValue) event).getValue());
        }
    }

    private void getDetailPlant(String gardenId, String plantId) {
        plantUseCase.getDetailPlant(gardenId, plantId, new ResponseCallback<PlantDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<PlantDetailRes> response) {
                plantDetail.postValue(response.getData().data.getPlant());
                tanamanType.postValue(response.getData().data.getPlant().getType());
            }

            @Override
            public void onFailure(BaseResultResponse<PlantDetailRes> response) {

            }
        });
    }

    private void getDetailGarden(String gardenId) {
        gardenUseCase.getDetailGarden(gardenId, new ResponseCallback<GardenDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<GardenDetailRes> response) {
                gardenDetail.postValue(response.getData().getData().getGarden());
            }

            @Override
            public void onFailure(BaseResultResponse<GardenDetailRes> response) {

            }
        });
    }

    private void postDetailPlant(String gardenId, PlantReq plantReq) {
        plantUseCase.postPlant(gardenId, plantReq, new ResponseCallback<PlantDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<PlantDetailRes> response) {

            }

            @Override
            public void onFailure(BaseResultResponse<PlantDetailRes> response) {

            }
        });
    }

    private void putDetailPlant(String gardenId, String plantId, PlantReqPut plantReq) {
        plantUseCase.putPlant(gardenId, plantId, plantReq, new ResponseCallback<PlantDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<PlantDetailRes> response) {
                plantDetail.postValue(response.getData().data.getPlant());
            }

            @Override
            public void onFailure(BaseResultResponse<PlantDetailRes> response) {

            }
        });
    }

    private void getReminder(String gardenId){
        reminderUseCase.getReminder(gardenId, new ResponseCallback<ReminderListRes>() {
            @Override
            public void onSuccess(BaseResultResponse<ReminderListRes> response) {
                reminderDetail.postValue(response.getData().data.getReminder().get(0));
            }

            @Override
            public void onFailure(BaseResultResponse<ReminderListRes> response) {

            }
        });
    }
}
