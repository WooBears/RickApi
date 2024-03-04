package com.example.rickapi.di

import com.example.rickapi.data.CartoonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor() : HttpLoggingInterceptor {
        val interpolator = HttpLoggingInterceptor()
        interpolator.level = HttpLoggingInterceptor.Level.BODY
        return interpolator
    }

    @Provides
    fun provideCartoonApiService(retrofit: Retrofit) : CartoonApiService {
        return retrofit.create(CartoonApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatcherIO() = Dispatchers.IO
}