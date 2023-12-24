package com.example.planties.di;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.data.auth.remote.AuthRemoteDataSource;
import com.example.planties.data.auth.remote.AuthRemoteDataSourceImpl;
import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.data.garden.remote.GardenRemoteDataSourceImpl;
import com.example.planties.data.garden.remote.GardenRemoteDataSource;
import com.example.planties.data.garden.remote.network.GardenService;
import com.example.planties.data.plant.remote.PlantRemoteDataSource;
import com.example.planties.data.plant.remote.PlantRemoteDataSourceImpl;
import com.example.planties.data.plant.remote.network.PlantService;
import com.example.planties.data.user.remote.UserRemoteDataSource;
import com.example.planties.data.user.remote.UserRemoteDataSourceImpl;
import com.example.planties.data.user.remote.network.UserService;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataSourceModule {
    @Provides
    public AuthRemoteDataSource provideAuthRemoteDataSource(AuthService authService) {
        return new AuthRemoteDataSourceImpl(authService);
    }
    @Provides
    public GardenRemoteDataSource provideGardenRemoteDataSource(GardenService gardenService, TokenHandler tokenHandler) {
        return new GardenRemoteDataSourceImpl(gardenService, tokenHandler);
    }
    @Provides
    public PlantRemoteDataSource providePlantRemoteDataSource(PlantService plantService, TokenHandler tokenHandler) {
        return new PlantRemoteDataSourceImpl(plantService, tokenHandler);
    }
    @Provides
    public UserRemoteDataSource provideUserRemoteDataSource(UserService userService, TokenHandler tokenHandler) {
        return new UserRemoteDataSourceImpl(userService, tokenHandler);
    }
}
