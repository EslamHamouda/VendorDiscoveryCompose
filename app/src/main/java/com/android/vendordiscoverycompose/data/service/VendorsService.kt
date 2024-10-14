package com.android.vendor.data.service

import com.android.vendordiscoverycompose.data.model.BaseResponse
import com.android.vendordiscoverycompose.data.model.response.VendorCategoryResponse
import com.android.vendordiscoverycompose.data.model.response.VendorsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VendorsService {
    @GET("getvendorcategory")
    suspend fun getVendorCategory(): BaseResponse<List<VendorCategoryResponse>>

    @GET("vendors")
    suspend fun getVendors(
        @Query("from") from: Int,
        @Query("userId") vendorId: String? = null,
        @Query("searchKey") searchKey: String? = null
    ): BaseResponse<VendorsResponse>


}