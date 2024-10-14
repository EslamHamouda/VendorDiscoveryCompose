package com.android.vendordiscoverycompose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.vendordiscoverycompose.presentation.details.ui.DetailsScreen
import com.android.vendordiscoverycompose.presentation.vendors.ui.VendorScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "vendor"
    ) {
        composable("vendor") {
            VendorScreen(onNavigate = {navController.navigate("details/$it")})
        }
        composable("details/{vendorId}") { backStackEntry ->
            backStackEntry.arguments?.getString("vendorId")?.let { DetailsScreen(vendorId = it) }
        }
    }
}