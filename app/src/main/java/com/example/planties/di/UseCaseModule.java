package com.example.planties.di;

import com.example.planties.data.auth.repository.AuthRepositoryImpl;
import com.example.planties.data.garden.repository.GardenRepositoryImpl;
import com.example.planties.data.leaderboards.repository.LeaderboardsRepositoryImpl;
import com.example.planties.data.plant.repository.PlantRepositoryImpl;
import com.example.planties.data.reminder.repository.ReminderRepositoryImpl;
import com.example.planties.data.scan.repository.ScanRepositoryImpl;
import com.example.planties.data.user.repository.UserRepositoryImpl;
import com.example.planties.domain.auth.usecase.AuthUseCase;
import com.example.planties.domain.garden.usecase.GardenUseCase;
import com.example.planties.domain.oxygen_leaderboard.usecase.LeaderboardsUseCase;
import com.example.planties.domain.plant.usecase.PlantUseCase;
import com.example.planties.domain.reminder.usecase.ReminderUseCase;
import com.example.planties.domain.scan.usecase.ScanUseCase;
import com.example.planties.domain.user.usecase.UserUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {
    @Provides
    public AuthUseCase provideAuthUseCase(AuthRepositoryImpl authRepository) {
        return new AuthUseCase(authRepository);
    }

    @Provides
    public GardenUseCase provideGardenUseCase(GardenRepositoryImpl gardenRepository) {
        return new GardenUseCase(gardenRepository);
    }

    @Provides
    public PlantUseCase providePlantUseCase(PlantRepositoryImpl plantRepository) {
        return new PlantUseCase(plantRepository);
    }

    @Provides
    public UserUseCase provideUserUseCase(UserRepositoryImpl userRepository) {
        return new UserUseCase(userRepository);
    }

    @Provides
    public ReminderUseCase provideReminderUseCase(ReminderRepositoryImpl reminderRepository){
        return new ReminderUseCase(reminderRepository);
    }

    @Provides
    public LeaderboardsUseCase provideLeaderboardsUseCase(LeaderboardsRepositoryImpl leaderboardsRepository){
        return new LeaderboardsUseCase(leaderboardsRepository);
    }

    @Provides
    public ScanUseCase provideScanUseCase(ScanRepositoryImpl scanRepository) {
        return new ScanUseCase(scanRepository);
    }
}
