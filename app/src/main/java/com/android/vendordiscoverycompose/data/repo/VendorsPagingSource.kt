package com.android.vendordiscoverycompose.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.vendordiscoverycompose.data.mapper.VendorsDomainMapper
import com.android.vendor.data.service.VendorsService
import com.android.vendordiscoverycompose.domain.model.VendorsDomainModel


class VendorsPagingSource (
    private val vendorId: String? = null,
    private val searchKey: String? = null,
    private val service: VendorsService
) :
    PagingSource<Int, VendorsDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VendorsDomainModel> {

        return try {
            val position = params.key ?: 0
            val response = service.getVendors(
                position,
                vendorId,
                searchKey
            ).data?.vendors?.map { dto -> VendorsDomainMapper.toDomain(dto) }.orEmpty()
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, VendorsDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}