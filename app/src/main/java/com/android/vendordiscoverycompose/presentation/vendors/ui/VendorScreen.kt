package com.android.vendordiscoverycompose.presentation.vendors.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.android.vendordiscoverycompose.R
import com.android.vendordiscoverycompose.domain.model.VendorCategoryDomainModel
import com.android.vendordiscoverycompose.domain.model.VendorsDomainModel
import com.android.vendordiscoverycompose.presentation.vendors.viewmodel.VendorCategoryStates
import com.android.vendordiscoverycompose.presentation.vendors.viewmodel.VendorsViewModel
import com.android.vendordiscoverycompose.ui.theme.AppFontFamily400
import com.android.vendordiscoverycompose.ui.theme.AppFontFamily500
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun VendorScreen(onNavigate: (String)->Unit, viewModel: VendorsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getVendorCategory()
        viewModel.getVendors()
    }

    val state by viewModel.getVendorCategoryResponse.collectAsState()
    val vendorContentPagedList = viewModel.getVendorsResponse.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        WhereToBuyTitle()

        Separator()
        Spacer(modifier = Modifier.height(16.dp))

        var categoryName by remember { mutableStateOf("") }
        var searchText by remember { mutableStateOf("") }
        VendorSearchField(searchText = searchText, onSearchTextChanged = {
            searchText = it
            viewModel.getVendors(categoryName, searchText)
        })

        WhereToBuyHeader()
        SeeWhereToBuySubheader()

        when (state) {
            is VendorCategoryStates.Success -> {
                VendorCategoriesList(
                    vendorCategories = (state as VendorCategoryStates.Success).value
                ) {
                    categoryName = it
                    viewModel.getVendors(categoryName)
                }
            }

            is VendorCategoryStates.Loading -> {
                LoadingIndicator()
            }

            is VendorCategoryStates.Failure -> {
                ErrorScreen(throwable = (state as VendorCategoryStates.Failure).throwable)
            }
        }

        VendorContentList(vendorContent = vendorContentPagedList) { onNavigate(it) }

    }
}

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator()
}

@Composable
fun ErrorScreen(throwable: Throwable) {
    Text(text = "Error: ${throwable.message}")
}

// Improved: Extracted the title text into a separate composable for better organization
@Composable
fun WhereToBuyTitle() {
    Text(
        text = stringResource(id = R.string.where_to_buy),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 64.dp)
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.tv_primary_color),
        fontSize = 16.sp,
        style = TextStyle(fontFamily = AppFontFamily500),
        lineHeight = 2.sp // 16sp + 2sp extra spacing
    )
}

// Improved: Extracted the search field into a separate composable for better organization and reusability
@Composable
fun VendorSearchField(searchText: String, onSearchTextChanged: (String) -> Unit) {
    BasicTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp)
            .background(
                color = Color(0xffF9FAFC),
                shape = RoundedCornerShape(8.dp)
            ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Color(0xff5E6160)
        ),
        singleLine = true,
        cursorBrush = SolidColor(Color(0xff5E6160)),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outlined_search),
                    contentDescription = "Search Icon"
                )
                Spacer(modifier = Modifier.width(16.dp))
                // Improved: Use a more descriptive name for the placeholder text composable
                SearchPlaceholderText(searchText = searchText, innerTextField = innerTextField)
            }
        }
    )
}

// Improved: Extracted the placeholder text into a separate composable for better readability
@Composable
fun SearchPlaceholderText(searchText: String, innerTextField: @Composable () -> Unit) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        if (searchText.isEmpty()) {
            Text(
                text = stringResource(id = R.string.search_where_to_buy),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xff5E6160)
                ),
                fontFamily = AppFontFamily400,
                lineHeight = 2.sp
            )
        }
        innerTextField()
    }
}

@Composable
fun Separator() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFF6F6F6))
    )
}

@Composable
fun WhereToBuyHeader() {
    Text(
        text = stringResource(id = R.string.where_to_buy),
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 24.dp
            ), // Assuming 16.dp is the start margin of search_categories
        color = colorResource(id = R.color.tv_primary_color),
        style = TextStyle(fontFamily = AppFontFamily500),
        fontSize = 20.sp,
        lineHeight = 1.sp // 20sp - 1sp extra spacing
    )
}

@Composable
fun SeeWhereToBuySubheader() {
    Text(
        text = stringResource(id = R.string.see_where_you_can_buy_it),
        modifier = Modifier
            .padding(start = 16.dp), // Assuming 16.dp is the start margin of tv_header
        color = Color(0xFF5E6160),
        style = TextStyle(fontFamily = AppFontFamily400),
        fontSize = 14.sp,
        lineHeight = 2.sp // 14sp + 2sp extra spacing
    )
}

@Composable
fun VendorCategoriesList(
    vendorCategories: List<VendorCategoryDomainModel>,
    onCategorySelected: (String) -> Unit
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    LazyRow(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(vendorCategories) { category ->
            VendorCategoryItem(
                categoryName = category.arabicName,
                isSelected = category.arabicName == selectedCategory,
                onCategoryClick = {
                    selectedCategory = category.arabicName
                    onCategorySelected(it)
                }
            )
        }
    }
}

@Composable
fun VendorCategoryItem(
    categoryName: String,
    isSelected: Boolean,
    onCategoryClick: (String) -> Unit
) {
    val backgroundDrawable = if (isSelected) {
        LocalContext.current.getDrawable(R.drawable.bg_rounded_vendor_category_active)
    } else {
        LocalContext.current.getDrawable(R.drawable.bg_rounded_vendor_category)
    }
    val textColor = if (isSelected) {
        colorResource(id = R.color.progress_bar_color)
    } else {
        colorResource(id = R.color.tv_primary_color)
    }

    Box {
        Image(
            painter = rememberDrawablePainter(drawable = backgroundDrawable),
            contentDescription = null, // Provide a content description if needed
            modifier = Modifier
                .matchParentSize()
        )
        Text(
            text = categoryName,
            modifier = Modifier
                .clickable { onCategoryClick(categoryName) }
                .padding(horizontal = 16.dp, vertical = 3.dp),
            color = textColor,
            fontSize = 14.sp,
            style = TextStyle(fontFamily = AppFontFamily400),
            lineHeight = 2.sp
        )
    }
}

@Composable
fun VendorContentList(vendorContent: LazyPagingItems<VendorsDomainModel>, onVendorClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        items(
            count = vendorContent.itemCount
        ) { content ->
            val vendor = vendorContent[content]
            if (vendor != null) {
                VendorContentItem(modifier = Modifier.clickable { onVendorClick(vendor.id) } ,vendorName = vendor.nameAr, vendorAddress = vendor.address)
            }
        }
    }
}

@Composable
fun VendorContentItem(modifier: Modifier = Modifier, vendorName: String, vendorAddress: String) {
    Column(modifier = modifier.padding(16.dp)) { // Padding from the ConstraintLayout
        Text(
            text = vendorName,
            color = colorResource(id = R.color.tv_primary_color),
            fontSize = 16.sp,
            style = TextStyle(fontFamily = AppFontFamily500),
            lineHeight = 2.sp // 16sp + 2sp extra spacing
        )
        Text(
            text = vendorAddress,
            modifier = Modifier.padding(top = 4.dp), // Small spacing between texts
            color = Color(0xFF5E6160),
            fontSize = 14.sp,
            style = TextStyle(fontFamily = AppFontFamily400),
            lineHeight = 2.sp // 14sp + 2sp extra spacing
        )
    }
}

// Data class to represent vendor content
data class VendorContent(val name: String, val address: String)

@Preview(showSystemUi = true)
@Composable
fun VendorScreenPreview() {
    VendorScreen({})
}