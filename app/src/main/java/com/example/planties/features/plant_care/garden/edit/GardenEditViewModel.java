package com.example.planties.features.plant_care.garden.edit;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.garden.remote.dto.GardenResModel;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantResListDataModel;
import com.example.planties.data.reminder.remote.dto.ReminderDetailRes;
import com.example.planties.data.reminder.remote.dto.ReminderListRes;
import com.example.planties.data.reminder.remote.dto.ReminderReq;
import com.example.planties.data.reminder.remote.dto.ReminderResModel;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;
import com.example.planties.domain.reminder.usecase.ReminderUseCase;
import com.example.planties.features.plant_care.plant_detail.PlantDetailViewEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GardenEditViewModel extends ViewModel {
    private GardenUseCase gardenUseCase;
    private PlantUseCase plantUseCase;

    private ReminderUseCase reminderUseCase;

    @Inject
    public GardenEditViewModel(GardenUseCase gardenUseCase, PlantUseCase plantUseCase, ReminderUseCase reminderUseCase) {
        this.gardenUseCase = gardenUseCase;
        this.plantUseCase = plantUseCase;
        this.reminderUseCase = reminderUseCase;
    }

    private MutableLiveData<GardenResModel> gardenDetail = new MutableLiveData<>();

    public MutableLiveData<GardenResModel> getGardenDetail() {
        return gardenDetail;
    }

    private MutableLiveData<PlantResListDataModel> plantList = new MutableLiveData<>();

    public MutableLiveData<PlantResListDataModel> getPlantList() {
        return plantList;
    }

    private MutableLiveData<ReminderResModel> reminderDetail = new MutableLiveData<>();

    public LiveData<ReminderResModel> getReminder() {
        return reminderDetail;
    }

    private MutableLiveData<List<String>> imageList = new MutableLiveData<>();

    public LiveData<List<String>> getImageList() {
        return imageList;
    }


    public void processEvent(GardenEditViewEvent event) {
        if (event instanceof GardenEditViewEvent.OnLoadGarden) {
            getDetailGarden(((GardenEditViewEvent.OnLoadGarden) event).gardenId);
            getPlantsWithGardenId(((GardenEditViewEvent.OnLoadGarden) event).gardenId);
        } else if (event instanceof GardenEditViewEvent.OnSaveEdit) {
            if (((GardenEditViewEvent.OnSaveEdit) event).gardenId == null) {
                postGarden(((GardenEditViewEvent.OnSaveEdit) event).gardenReq);
            } else {
                putDetailGarden(((GardenEditViewEvent.OnSaveEdit) event).gardenId, ((GardenEditViewEvent.OnSaveEdit) event).gardenReq);
            }
        } else if (event instanceof GardenEditViewEvent.OnAddImage) {
            if (((GardenEditViewEvent.OnAddImage) event).gardenId == null) {
                postGarden(((GardenEditViewEvent.OnAddImage) event).gardenReq);
            } else {
                putDetailGarden(((GardenEditViewEvent.OnAddImage) event).gardenId, ((GardenEditViewEvent.OnAddImage) event).gardenReq);
            }
        } else if (event instanceof GardenEditViewEvent.OnAddReminder) {
            postReminder(((GardenEditViewEvent.OnAddReminder) event).getGardenId(), ((GardenEditViewEvent.OnAddReminder) event).getReminderReq());
        } else if (event instanceof GardenEditViewEvent.OnLoadReminder) {
            getReminderDetail(((GardenEditViewEvent.OnLoadReminder) event).getGardenId());
        } else if (event instanceof GardenEditViewEvent.OnAddPostImage) {
            List<String> list = imageList.getValue();

            if (list == null) {
                list = new ArrayList<>();
            }

            String newImage = ((GardenEditViewEvent.OnAddPostImage) event).getImage();
            Log.d("GardenEditViewEvent", "processEvent: " + newImage);

            list.add(newImage);

            imageList.postValue(list);
        } else if (event instanceof GardenEditViewEvent.OnAddGarden) {
            postGarden(((GardenEditViewEvent.OnAddGarden) event).getGardenReq());
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

    private void getReminderDetail(String gardenId) {
        reminderUseCase.getReminder(gardenId, new ResponseCallback<ReminderListRes>() {

            @Override
            public void onSuccess(BaseResultResponse<ReminderListRes> response) {
                Log.d("Reminder", "Get Reminder Success");
                reminderDetail.setValue(response.getData().data.getReminder().get(0));
            }

            @Override
            public void onFailure(BaseResultResponse<ReminderListRes> response) {

            }
        });
    }

    private void postReminder(String gardenId, ReminderReq reminderReq) {
        reminderUseCase.postReminder(gardenId, reminderReq, new ResponseCallback<ReminderDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<ReminderDetailRes> response) {
                Log.d("Reminder", "Reminder Success");
                reminderDetail.setValue(response.getData().data.getReminder());
            }

            @Override
            public void onFailure(BaseResultResponse<ReminderDetailRes> response) {

            }
        });
    }
}
