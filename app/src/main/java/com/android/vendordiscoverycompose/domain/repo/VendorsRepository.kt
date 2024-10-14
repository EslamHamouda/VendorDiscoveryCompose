package com.android.vendordiscoverycompose.domain.repo

import androidx.paging.PagingData
import com.android.vendordiscoverycompose.domain.model.VendorCategoryDomainModel
import com.android.vendordiscoverycompose.domain.model.VendorsDomainModel
import kotlinx.coroutines.flow.Flow

interface VendorsRepository {
    suspend fun getVendorCategory(): List<VendorCategoryDomainModel>
    suspend fun getVendors(vendorId: String? = null, searchKey: String? = null):
            Flow<PagingData<VendorsDomainModel>>
}