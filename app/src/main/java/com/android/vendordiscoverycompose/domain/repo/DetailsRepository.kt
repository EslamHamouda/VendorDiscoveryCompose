package com.android.vendordiscoverycompose.domain.repo

import com.android.vendordiscoverycompose.domain.model.MerchantDomainModel

interface DetailsRepository {
    suspend fun getMerchants(vendorId:String): List<MerchantDomainModel>
}