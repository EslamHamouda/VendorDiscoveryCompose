package com.android.vendordiscoverycompose.data.mapper

import com.android.vendordiscoverycompose.data.model.response.VendorRemoteModel
import com.android.vendordiscoverycompose.domain.model.VendorsDomainModel

object VendorsDomainMapper{
    fun toDomain(from: VendorRemoteModel) = VendorsDomainModel(
        id = from.id,
        nameEn = from.nameEn,
        nameAr = from.nameAr,
        address = from.address
    )
}