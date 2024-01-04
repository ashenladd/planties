package com.example.planties.features.admin;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.user.remote.dto.AdminRes;
import com.example.planties.data.user.remote.dto.AdminResModel;
import com.example.planties.domain.auth.usecase.AuthUseCase;
import com.example.planties.domain.user.usecase.UserUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AdminViewModel extends ViewModel {
    private final UserUseCase userUseCase;
    private final AuthUseCase authUseCase;

    @Inject
    public AdminViewModel(UserUseCase userUseCase, AuthUseCase authUseCase) {
        this.userUseCase = userUseCase;
        this.authUseCase = authUseCase;

        getAdmin();
    }

    private final MutableLiveData<AdminResModel> admin = new MutableLiveData<>();
    public LiveData<AdminResModel> getAdminLive() {
        return admin;
    }

    public void processEvent (AdminViewEvent event){
        if (event instanceof AdminViewEvent.OnRefresh) {
            getAdmin();
        } else if (event instanceof AdminViewEvent.OnLogout) {
            authUseCase.logout();
        }
    }

    void getAdmin() {
        userUseCase.getAdmin(new ResponseCallback<AdminRes>() {
            @Override
            public void onSuccess(BaseResultResponse<AdminRes> response) {
                if (response.isSuccess()) {
                    admin.setValue(response.getData().data.getAdminPage());
                }
            }

            @Override
            public void onFailure(BaseResultResponse<AdminRes> response) {

            }
        });
    }
}
