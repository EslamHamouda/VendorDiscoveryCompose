package com.android.vendordiscoverycompose.presentation.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.vendordiscoverycompose.R
import com.android.vendordiscoverycompose.domain.model.MerchantDomainModel
import com.android.vendordiscoverycompose.presentation.details.viewmodel.DetailsStates
import com.android.vendordiscoverycompose.presentation.details.viewmodel.DetailsViewModel
import com.android.vendordiscoverycompose.presentation.vendors.ui.ErrorScreen
import com.android.vendordiscoverycompose.presentation.vendors.ui.LoadingIndicator
import com.android.vendordiscoverycompose.presentation.vendors.ui.VendorCategoriesList
import com.android.vendordiscoverycompose.presentation.vendors.viewmodel.VendorCategoryStates
import com.android.vendordiscoverycompose.presentation.vendors.viewmodel.VendorsViewModel
import com.android.vendordiscoverycompose.ui.theme.AppFontFamily500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(vendorId: String, viewModel: DetailsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getMerchants(vendorId)
    }

    val state by viewModel.getMerchantsResponse.collectAsState()
    var vendorName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(modifier = Modifier
                .height(48.dp)
                .wrapContentHeight(),
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                        Text(
                            text = vendorName, // Replace with actual data or state
                            modifier = Modifier.wrapContentSize(),
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.tv_primary_color),
                            fontSize = 16.sp,
                            style = TextStyle(fontFamily =  AppFontFamily500),
                            lineHeight = 2.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(modifier = Modifier.size(48.dp),onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_nav_back),
                            contentDescription = "Back",
                            modifier = Modifier.scale(scaleX = -1f, scaleY = 1f)
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = Color.White, // Change the background color
                    navigationIconContentColor = Color(0xff6A768C), // Change the back button color
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
        ) {
            Separator()

            when (state) {
                is DetailsStates.Success -> {
                    MerchantList(merchants = (state as DetailsStates.Success).value)
                    vendorName = (state as DetailsStates.Success).value[0].vendorArabicName
                }

                is DetailsStates.Loading -> {
                    LoadingIndicator()
                }

                is DetailsStates.Failure -> {
                    ErrorScreen(throwable = (state as DetailsStates.Failure).throwable)
                }
            }
        }
    }
}



@Composable
fun MerchantList(merchants: List<MerchantDomainModel>) {
    LazyColumn {
        items(merchants) { merchant ->
            MerchantItem(merchant = merchant)
        }
    }
}

@Composable
fun MerchantItem(merchant: MerchantDomainModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 21.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = merchant.arabicName,
                color = colorResource(id = R.color.tv_primary_color),
                fontSize = 16.sp,
                style = TextStyle(fontFamily =  AppFontFamily500),
                lineHeight = 2.sp
            )
            Text(
                text = merchant.address,
                modifier = Modifier.padding(top = 4.dp),
                color = colorResource(id = R.color.tv_secondary_color),
                fontSize = 14.sp,
                style = TextStyle(fontFamily =  AppFontFamily500),
                lineHeight = 2.sp
            )
        }

        Row {
            IconButton(modifier = Modifier
                .width(48.dp)
                .height(48.dp),onClick = { /* Handle map action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = "Map",
                    tint = Color.Unspecified
                )
            }
            IconButton(modifier = Modifier
                .width(48.dp)
                .height(48.dp),onClick = { /* Handle phone action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "Phone",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

data class Merchant(val name: String, val address: String)

@Composable
fun Separator() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFF6F6F6))
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen("")
}