package com.android.vendordiscoverycompose.domain.model

 data class MerchantDomainModel(
    val id: String,
    val name: String,
    val arabicName: String,
    val address: String,
    val longitude: Double,
    val latitude: Double,
    val phoneNumber: String,
    val vendorName: String,
    val vendorArabicName: String
)
