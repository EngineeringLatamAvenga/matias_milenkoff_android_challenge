package com.mtmilenkoff.locationapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.compose.LocationAppTheme
import com.mtmilenkoff.locationapp.MainViewModel.SideEffect
import com.mtmilenkoff.locationapp.views.LocationsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            LocationAppTheme {
                val state = mainViewModel.uiState
                splashScreen.setKeepOnScreenCondition { state.isLoading }
                LocationsScreen(mainViewModel)

                val context = LocalContext.current

                LaunchedEffect(Unit) {
                    mainViewModel.sideEffect.collect { effect ->
                        when (effect) {
                            is SideEffect.ShowError -> {
                                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}
