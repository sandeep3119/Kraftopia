package com.example.kraftopia.di

import android.content.Context
import android.content.SharedPreferences
import com.example.kraftopia.BuildConfig
import com.example.kraftopia.data.api.ApiHelper
import com.example.kraftopia.data.api.ApiHelperImpl
//import com.example.kraftopia.data.api.ApiHelperImpl
import com.example.kraftopia.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideBaseUrl()=BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if(BuildConfig.DEBUG){
     val loggingInterceptor =HttpLoggingInterceptor()
     loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
     OkHttpClient.Builder()
         .addInterceptor(loggingInterceptor)
         .build()

    }else OkHttpClient.Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit (okHttpClient: OkHttpClient,BASE_URL:String):Retrofit=
        Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context:Context):SharedPreferences{
        return context.getSharedPreferences("user_session",0)
    }
}