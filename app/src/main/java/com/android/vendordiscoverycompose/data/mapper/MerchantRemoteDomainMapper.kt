package com.android.vendordiscoverycompose.data.mapper

import com.android.vendordiscoverycompose.data.model.response.MerchantRemoteModel
import com.android.vendordiscoverycompose.domain.model.MerchantDomainModel

object MerchantRemoteDomainMapper {
    fun toDomain(from: MerchantRemoteModel) = MerchantDomainModel(
        id = from.id,
        name = from.name,
        arabicName = from.arabicName,
        address = from.address,
        longitude = from.longitude,
        latitude = from.latitude,
        phoneNumber = from.phoneNumber,
        vendorName = from.vendor.name,
        vendorArabicName = from.vendor.arabicName
    )
}