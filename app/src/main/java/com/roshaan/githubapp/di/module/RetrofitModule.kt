package com.roshaan.githubapp.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providesRetrofit(
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()


    @JvmStatic
    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson) = GsonConverterFactory.create(gson)


    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()


    @JvmStatic
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}