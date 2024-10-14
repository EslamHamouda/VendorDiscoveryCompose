package com.android.vendordiscoverycompose.di

import com.android.vendordiscoverycompose.data.repo.VendorsRepositoryImpl
import com.android.vendor.data.service.VendorsService
import com.android.vendordiscoverycompose.data.repo.DetailsRepositoryImpl
import com.android.vendordiscoverycompose.data.service.DetailsService
import com.android.vendordiscoverycompose.domain.repo.DetailsRepository
import com.android.vendordiscoverycompose.domain.repo.VendorsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun provideVendorRepository(service: VendorsService): VendorsRepository {
        return VendorsRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideDetailsRepository(service: DetailsService): DetailsRepository {
        return DetailsRepositoryImpl(service)
    }
}