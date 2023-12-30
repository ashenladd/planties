package com.example.planties.di;

import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.data.garden.remote.network.GardenService;
import com.example.planties.data.leaderboards.remote.network.LeaderboardsService;
import com.example.planties.data.plant.remote.network.PlantService;
import com.example.planties.data.reminder.remote.network.ReminderService;
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

    @Provides
    public static ReminderService provideReminderService(Retrofit retrofit) {
        return retrofit.create(ReminderService.class);
    }

    @Provides
    public static LeaderboardsService provideLeaderboardsService(Retrofit retrofit) {
        return retrofit.create(LeaderboardsService.class);
    }
}
