package com.android.vendordiscoverycompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.vendordiscoverycompose.presentation.AppNavHost
import com.android.vendordiscoverycompose.presentation.vendors.ui.VendorScreen
import com.android.vendordiscoverycompose.ui.theme.VendorDiscoveryComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VendorDiscoveryComposeTheme {
                AppNavHost()
            }
        }
    }
}
