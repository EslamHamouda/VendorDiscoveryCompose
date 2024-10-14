package com.android.vendordiscoverycompose.di

import com.android.vendordiscoverycompose.domain.repo.DetailsRepository
import com.android.vendordiscoverycompose.domain.repo.VendorsRepository
import com.android.vendordiscoverycompose.domain.useCase.GetMerchantsUseCase
import com.android.vendordiscoverycompose.domain.useCase.GetVendorCategoryUseCase
import com.android.vendordiscoverycompose.domain.useCase.GetVendorsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetVendorCategoryUseCase(repository: VendorsRepository): GetVendorCategoryUseCase {
        return GetVendorCategoryUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetVendorsUseCase(repository: VendorsRepository): GetVendorsUseCase {
        return GetVendorsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMerchantsUseCase(repository: DetailsRepository): GetMerchantsUseCase {
        return GetMerchantsUseCase(repository)
    }
}