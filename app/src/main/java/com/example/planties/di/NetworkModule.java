package com.example.planties.di;

import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.data.garden.remote.network.GardenService;
import com.example.planties.data.plant.remote.network.PlantService;
import com.example.planties.data.user.remote.network.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Provides
    public static AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    public static GardenService provideGardenService(Retrofit retrofit) {
        return retrofit.create(GardenService.class);
    }
    @Provides
    public static PlantService providePlantService(Retrofit retrofit) {
        return retrofit.create(PlantService.class);
    }
    @Provides
    public static UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
