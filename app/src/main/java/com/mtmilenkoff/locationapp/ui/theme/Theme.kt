package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.mtmilenkoff.locationapp.ui.theme.Typography
import com.mtmilenkoff.locationapp.ui.theme.backgroundDark
import com.mtmilenkoff.locationapp.ui.theme.backgroundDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.backgroundDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.backgroundLight
import com.mtmilenkoff.locationapp.ui.theme.backgroundLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.backgroundLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.errorContainerDark
import com.mtmilenkoff.locationapp.ui.theme.errorContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.errorContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.errorContainerLight
import com.mtmilenkoff.locationapp.ui.theme.errorContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.errorContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.errorDark
import com.mtmilenkoff.locationapp.ui.theme.errorDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.errorDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.errorLight
import com.mtmilenkoff.locationapp.ui.theme.errorLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.errorLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseOnSurfaceDark
import com.mtmilenkoff.locationapp.ui.theme.inverseOnSurfaceDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseOnSurfaceLight
import com.mtmilenkoff.locationapp.ui.theme.inverseOnSurfaceLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseOnSurfaceLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.inversePrimaryDark
import com.mtmilenkoff.locationapp.ui.theme.inversePrimaryDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.inversePrimaryDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.inversePrimaryLight
import com.mtmilenkoff.locationapp.ui.theme.inversePrimaryLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.inversePrimaryLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseSurfaceDark
import com.mtmilenkoff.locationapp.ui.theme.inverseSurfaceDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseSurfaceDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseSurfaceLight
import com.mtmilenkoff.locationapp.ui.theme.inverseSurfaceLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.inverseSurfaceLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onBackgroundDark
import com.mtmilenkoff.locationapp.ui.theme.onBackgroundDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onBackgroundDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onBackgroundLight
import com.mtmilenkoff.locationapp.ui.theme.onBackgroundLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onBackgroundLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorContainerDark
import com.mtmilenkoff.locationapp.ui.theme.onErrorContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorContainerLight
import com.mtmilenkoff.locationapp.ui.theme.onErrorContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorDark
import com.mtmilenkoff.locationapp.ui.theme.onErrorDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorLight
import com.mtmilenkoff.locationapp.ui.theme.onErrorLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onErrorLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryContainerDark
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryContainerLight
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryDark
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryLight
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onPrimaryLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryContainerDark
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryContainerLight
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryDark
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryLight
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSecondaryLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceDark
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceLight
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceVariantDark
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceVariantDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceVariantDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceVariantLight
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceVariantLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onSurfaceVariantLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryContainerDark
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryContainerLight
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryDark
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryLight
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.onTertiaryLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineDark
import com.mtmilenkoff.locationapp.ui.theme.outlineDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineLight
import com.mtmilenkoff.locationapp.ui.theme.outlineLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineVariantDark
import com.mtmilenkoff.locationapp.ui.theme.outlineVariantDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineVariantDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineVariantLight
import com.mtmilenkoff.locationapp.ui.theme.outlineVariantLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.outlineVariantLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryContainerDark
import com.mtmilenkoff.locationapp.ui.theme.primaryContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryContainerLight
import com.mtmilenkoff.locationapp.ui.theme.primaryContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryDark
import com.mtmilenkoff.locationapp.ui.theme.primaryDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryLight
import com.mtmilenkoff.locationapp.ui.theme.primaryLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.primaryLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.scrimDark
import com.mtmilenkoff.locationapp.ui.theme.scrimDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.scrimDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.scrimLight
import com.mtmilenkoff.locationapp.ui.theme.scrimLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.scrimLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryContainerDark
import com.mtmilenkoff.locationapp.ui.theme.secondaryContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryContainerLight
import com.mtmilenkoff.locationapp.ui.theme.secondaryContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryDark
import com.mtmilenkoff.locationapp.ui.theme.secondaryDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryLight
import com.mtmilenkoff.locationapp.ui.theme.secondaryLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.secondaryLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceBrightDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceBrightDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceBrightDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceBrightLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceBrightLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceBrightLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighestDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighestDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighestLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighestLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerHighestLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowestDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowestDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowestLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowestLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceContainerLowestLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceDimDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceDimDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceDimDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceDimLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceDimLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceDimLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceVariantDark
import com.mtmilenkoff.locationapp.ui.theme.surfaceVariantDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceVariantDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceVariantLight
import com.mtmilenkoff.locationapp.ui.theme.surfaceVariantLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.surfaceVariantLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryContainerDark
import com.mtmilenkoff.locationapp.ui.theme.tertiaryContainerDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryContainerDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryContainerLight
import com.mtmilenkoff.locationapp.ui.theme.tertiaryContainerLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryContainerLightMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryDark
import com.mtmilenkoff.locationapp.ui.theme.tertiaryDarkHighContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryDarkMediumContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryLight
import com.mtmilenkoff.locationapp.ui.theme.tertiaryLightHighContrast
import com.mtmilenkoff.locationapp.ui.theme.tertiaryLightMediumContrast


private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun LocationAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
