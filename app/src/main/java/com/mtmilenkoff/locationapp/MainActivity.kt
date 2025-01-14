package com.mtmilenkoff.locationapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.compose.LocationAppTheme
import com.mtmilenkoff.locationapp.views.LocationsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LocationAppTheme {
                val state = mainViewModel.uiState
                splashScreen.setKeepOnScreenCondition { state.isLoading }
                LocationsScreen(mainViewModel)
            }
        }
    }
}
