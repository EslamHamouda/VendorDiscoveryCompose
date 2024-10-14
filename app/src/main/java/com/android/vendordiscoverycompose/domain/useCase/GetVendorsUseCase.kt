package com.android.vendordiscoverycompose.domain.useCase

import androidx.paging.PagingData
import com.android.vendordiscoverycompose.domain.model.VendorsDomainModel
import com.android.vendordiscoverycompose.domain.repo.VendorsRepository
import kotlinx.coroutines.flow.Flow


class GetVendorsUseCase(private val repository: VendorsRepository) {
    suspend operator fun invoke(
        vendorId: String? = null,
        searchKey: String? = null
    ): Flow<PagingData<VendorsDomainModel>> = repository.getVendors(vendorId, searchKey)
}