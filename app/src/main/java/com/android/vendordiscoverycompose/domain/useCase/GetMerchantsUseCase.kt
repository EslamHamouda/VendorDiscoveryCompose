package com.android.vendordiscoverycompose.domain.useCase

import com.android.vendordiscoverycompose.domain.model.MerchantDomainModel
import com.android.vendordiscoverycompose.domain.repo.DetailsRepository

class GetMerchantsUseCase (private val repository: DetailsRepository) {
    suspend operator fun invoke(vendorId:String): List<MerchantDomainModel> {
            return repository.getMerchants(vendorId)
    }
}