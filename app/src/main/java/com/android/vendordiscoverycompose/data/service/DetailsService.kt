package com.android.vendordiscoverycompose.data.service

import com.android.vendordiscoverycompose.data.model.BaseResponse
import com.android.vendordiscoverycompose.data.model.response.MerchantDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET("vendors/{vendorId}/merchants")
    suspend fun getMerchants(@Path("vendorId") vendorId:String): BaseResponse<MerchantDetailsResponse>
}