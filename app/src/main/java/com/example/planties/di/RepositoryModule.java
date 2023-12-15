package com.example.planties.di;

import com.example.planties.data.auth.remote.AuthRemoteDataSource;
import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.data.auth.repository.AuthRepositoryImpl;
import com.example.planties.data.garden.remote.GardenRemoteDataSource;
import com.example.planties.data.garden.remote.network.GardenService;
import com.example.planties.data.garden.repository.GardenRepositoryImpl;
import com.example.planties.domain.auth.usecase.AuthUseCase;
import com.example.planties.domain.garden.usecase.GardenUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Provides
    @Singleton
    public AuthRepositoryImpl provideAuthRepository(AuthRemoteDataSource authRemoteDataSource) {
        return new AuthRepositoryImpl(authRemoteDataSource);
    }

    @Provides
    @Singleton
    public GardenRepositoryImpl provideGardenRepository(GardenRemoteDataSource gardenRemoteDataSource) {
        return new GardenRepositoryImpl(gardenRemoteDataSource);
    }


}

