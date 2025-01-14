package com.mtmilenkoff.locationapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent
import com.mtmilenkoff.locationapp.ui.theme.LocationAppTheme
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

                LaunchedEffect(Unit) {
                    mainViewModel.onUiEvent(UIEvent.OnUpdateLocations)
                }

                splashScreen.setKeepOnScreenCondition { state.isLoading }
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

}
