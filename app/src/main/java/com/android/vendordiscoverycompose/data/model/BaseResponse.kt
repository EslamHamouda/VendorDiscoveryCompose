package com.android.vendordiscoverycompose.data.model

data class BaseResponse<T>(
    var status: Int,
    var message: String?,
    var data: T?
)
