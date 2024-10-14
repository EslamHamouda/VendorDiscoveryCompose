package com.android.vendordiscoverycompose.data.model.response

import com.android.vendordiscoverycompose.data.model.response.DetailsPromotionRemoteModel
import com.google.gson.annotations.SerializedName

data class VendorMerchantRemoteModel(
    var id: String,
    var address: String,
    var governorate: String?,
    var name: String,
    var arabicName: String,
    var marketingName: String,
    var phoneNumber: String,
    var description: String?,
    var lat: Double,
    var long: Double,
    var displayedOnApp: Boolean,
    @SerializedName("HasPromoation")
    var hasPromoation: Boolean,
    @SerializedName("promotion_list")
    var promotionList: ArrayList<DetailsPromotionRemoteModel>?
)
