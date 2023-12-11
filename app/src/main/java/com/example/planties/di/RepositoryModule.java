package com.example.planties.di;

import com.example.planties.data.source.remote.network.AuthService;
import com.example.planties.domain.auth.repository.AuthRepository;
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
    public AuthRepository provideAuthRepository(AuthService authService) {
        return new AuthRepository(authService);
    }

    @Provides
    @Singleton
    public AuthUseCase provideAuthUseCase(AuthRepository authRepository) {
        return new AuthUseCase(authRepository);
    }
}

