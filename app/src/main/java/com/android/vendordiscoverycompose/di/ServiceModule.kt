package com.android.vendordiscoverycompose.di

import com.android.vendor.data.service.VendorsService
import com.android.vendordiscoverycompose.data.service.DetailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun vendorsService(retrofit: Retrofit): VendorsService =
        retrofit.create(VendorsService::class.java)

    @Provides
    @Singleton
    fun detailsService(retrofit: Retrofit): DetailsService =
        retrofit.create(DetailsService::class.java)

}