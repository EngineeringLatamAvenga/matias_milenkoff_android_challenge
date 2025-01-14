package com.mtmilenkoff.locationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent
import com.mtmilenkoff.locationapp.ui.theme.LocationAppTheme

class MainActivity(private val mainViewModel: MainViewModel) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            LocationAppTheme {
                val state = mainViewModel.uiState
                mainViewModel.onUiEvent(UIEvent.OnUpdateLocations)
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
