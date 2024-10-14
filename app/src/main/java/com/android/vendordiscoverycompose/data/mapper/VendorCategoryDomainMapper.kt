package com.android.vendordiscoverycompose.data.mapper

import com.android.vendordiscoverycompose.data.model.response.VendorCategoryResponse
import com.android.vendordiscoverycompose.domain.model.VendorCategoryDomainModel

object VendorCategoryDomainMapper{
    fun toDomain(from: VendorCategoryResponse) = VendorCategoryDomainModel(
        id = from.id,
        name = from.name,
        arabicName = from.arabicName,
    )
}