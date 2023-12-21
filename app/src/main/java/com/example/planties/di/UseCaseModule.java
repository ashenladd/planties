package com.example.planties.di;

import com.example.planties.data.auth.repository.AuthRepositoryImpl;
import com.example.planties.data.garden.repository.GardenRepositoryImpl;
import com.example.planties.data.plant.repository.PlantRepositoryImpl;
import com.example.planties.domain.auth.usecase.AuthUseCase;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {
    @Provides
    @Singleton
    public AuthUseCase provideAuthUseCase(AuthRepositoryImpl authRepository) {
        return new AuthUseCase(authRepository);
    }

    @Provides
    @Singleton
    public GardenUseCase provideGardenUseCase(GardenRepositoryImpl gardenRepository) {
        return new GardenUseCase(gardenRepository);
    }

    @Provides
    @Singleton
    public PlantUseCase providePlantUseCase(PlantRepositoryImpl plantRepository) {
        return new PlantUseCase(plantRepository);
    }
}
