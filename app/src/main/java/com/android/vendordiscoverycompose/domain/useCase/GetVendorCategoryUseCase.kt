package com.android.vendordiscoverycompose.domain.useCase

import com.android.vendordiscoverycompose.domain.model.VendorCategoryDomainModel
import com.android.vendordiscoverycompose.domain.repo.VendorsRepository

class GetVendorCategoryUseCase(private val repository: VendorsRepository) {
    suspend operator fun invoke(): List<VendorCategoryDomainModel> = repository.getVendorCategory()

}