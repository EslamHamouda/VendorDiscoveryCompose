package com.android.vendordiscoverycompose.presentation.vendors.viewmodel

import com.android.vendordiscoverycompose.domain.model.VendorCategoryDomainModel

sealed class VendorCategoryStates{
    data class Success(val value: List<VendorCategoryDomainModel>): VendorCategoryStates()
    data class Failure(val throwable: Throwable): VendorCategoryStates()
    data class Loading(val isLoading: Boolean): VendorCategoryStates()
}