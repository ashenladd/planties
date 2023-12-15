package com.example.planties.di;

import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.data.auth.repository.AuthRepositoryImpl;
import com.example.planties.domain.auth.usecase.AuthUseCase;

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
    public AuthRepositoryImpl provideAuthRepository(AuthService authService) {
        return new AuthRepositoryImpl(authService);
    }

    @Provides
    @Singleton
    public AuthUseCase provideAuthUseCase(AuthRepositoryImpl authRepository) {
        return new AuthUseCase(authRepository);
    }
}

