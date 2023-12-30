package com.example.planties.di;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.data.auth.remote.AuthRemoteDataSource;
import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.data.auth.repository.AuthRepositoryImpl;
import com.example.planties.data.garden.remote.GardenRemoteDataSource;
import com.example.planties.data.garden.remote.network.GardenService;
import com.example.planties.data.garden.repository.GardenRepositoryImpl;
import com.example.planties.data.plant.remote.PlantRemoteDataSource;
import com.example.planties.data.plant.repository.PlantRepositoryImpl;
import com.example.planties.data.scan.remote.ScanRemoteDataSource;
import com.example.planties.data.scan.repository.ScanRepositoryImpl;
import com.example.planties.data.user.remote.UserRemoteDataSource;
import com.example.planties.data.user.repository.UserRepositoryImpl;
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
    public AuthRepositoryImpl provideAuthRepository(AuthRemoteDataSource authRemoteDataSource, TokenHandler tokenHandler) {
        return new AuthRepositoryImpl(authRemoteDataSource,tokenHandler);
    }

    @Provides
    public GardenRepositoryImpl provideGardenRepository(GardenRemoteDataSource gardenRemoteDataSource) {
        return new GardenRepositoryImpl(gardenRemoteDataSource);
    }

    @Provides
    public PlantRepositoryImpl providePlantRepository(PlantRemoteDataSource plantRemoteDataSource) {
        return new PlantRepositoryImpl(plantRemoteDataSource);
    }

    @Provides
    public UserRepositoryImpl provideUserRepository(UserRemoteDataSource userRemoteDataSource) {
        return new UserRepositoryImpl(userRemoteDataSource);
    }

    @Provides
    public ScanRepositoryImpl provideScanRepository(ScanRemoteDataSource scanRemoteDataSource) {
        return new ScanRepositoryImpl(scanRemoteDataSource);
    }
}

