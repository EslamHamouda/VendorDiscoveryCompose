package com.android.vendordiscoverycompose.data.model.response

data class MerchantDetailsResponse(
    var merchants: ArrayList<MerchantRemoteModel>?,
    var count: Int?
)