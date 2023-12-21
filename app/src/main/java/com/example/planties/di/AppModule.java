package com.example.planties.di;


import android.content.Context;

import com.example.planties.core.jwt.TokenHandler;
import com.example.planties.data.auth.remote.network.AuthService;
import com.example.planties.core.Constant;
import com.example.planties.data.garden.remote.network.GardenService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    @Named("applicationContext")
    public static Context provideApplicationContext(@ApplicationContext Context context) {
        return context;
    }

    @Provides
    @Singleton
    public static TokenHandler provideTokenHandler(@Named("applicationContext") Context context) {
        return new TokenHandler(context);
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constant.API_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
