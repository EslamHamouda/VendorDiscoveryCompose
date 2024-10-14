package com.android.vendordiscoverycompose.data.repo

import com.android.vendordiscoverycompose.data.mapper.MerchantRemoteDomainMapper
import com.android.vendordiscoverycompose.data.service.DetailsService
import com.android.vendordiscoverycompose.domain.model.MerchantDomainModel
import com.android.vendordiscoverycompose.domain.repo.DetailsRepository


class DetailsRepositoryImpl (private val service: DetailsService):
    DetailsRepository {
    override suspend fun getMerchants(vendorId: String): List<MerchantDomainModel> {
        val merchantsResponse = service.getMerchants(vendorId)
        return merchantsResponse.data?.merchants?.map { dto -> MerchantRemoteDomainMapper.toDomain(dto) }
            .orEmpty()
    }
}