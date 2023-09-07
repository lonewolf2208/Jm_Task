package com.example.jumpingmindstask.module

import android.app.Application
import androidx.room.Room
import com.example.jumpingmindstask.BuildConfig
import com.example.jumpingmindstask.network.Api
import com.example.jumpingmindstask.room.DogsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor = Interceptor { chain ->
        var request = chain.request()
        request = request?.newBuilder()
            ?.addHeader("X-RapidAPI-Key", BuildConfig.X_RapidAPI_Key)
            ?.addHeader("X-RapidAPI-Host", BuildConfig.X_RapidAPI_Host)
            ?.build()!!
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun providesClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    @Provides
    @Singleton
    fun provideDatabase(app: Application): DogsDatabase =
        Room.databaseBuilder(app, DogsDatabase::class.java, "dogs_database")
            .build()
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)
}