package com.example.planties.features.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.core.utils.ImageExtensions;
import com.example.planties.data.user.remote.dto.UserDetailRes;
import com.example.planties.data.user.remote.dto.UserResModel;
import com.example.planties.domain.auth.usecase.AuthUseCase;
import com.example.planties.domain.user.usecase.UserUseCase;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfileViewModel extends ViewModel {
    private final UserUseCase userUseCase;
    private final AuthUseCase authUseCase;

    private final MutableLiveData<UserResModel> user = new MutableLiveData<>();

    public LiveData<UserResModel> getUser() {
        return user;
    }

    @Inject
    public ProfileViewModel(AuthUseCase authUseCase, UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
        this.authUseCase = authUseCase;
        getProfile();
    }

    void logout() {
        authUseCase.logout();
    }

    private void getProfile() {
        userUseCase.getProfile(new ResponseCallback<UserDetailRes>() {
            @Override
            public void onSuccess(BaseResultResponse<UserDetailRes> response) {
                if (response.isSuccess()) {
                    user.setValue(response.getData().data.user);
                }
            }

            @Override
            public void onFailure(BaseResultResponse<UserDetailRes> response) {
            }
        });
    }
}
