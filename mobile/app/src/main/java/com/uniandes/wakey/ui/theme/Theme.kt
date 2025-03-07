package com.uniandes.wakey.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkScheme = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiaryContainer = onTertiaryContainer,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    outline = outline,
    outlineVariant = outlineVariant,
    scrim = scrim,
    inverseSurface = inverseSurface,
    inverseOnSurface = inverseOnSurface,
    inversePrimary = inversePrimary,
    surfaceDim = surfaceDim,
    surfaceBright = surfaceBright,
    surfaceContainerLowest = surfaceContainerLowest,
    surfaceContainerLow = surfaceContainerLow,
    surfaceContainer = surfaceContainer,
    surfaceContainerHigh = surfaceContainerHigh,
    surfaceContainerHighest = surfaceContainerHighest,
)

private val mediumContrastColorScheme = darkColorScheme(
    primary = primaryMediumContrast,
    onPrimary = onPrimaryMediumContrast,
    primaryContainer = primaryContainerMediumContrast,
    onPrimaryContainer = onPrimaryContainerMediumContrast,
    secondary = secondaryMediumContrast,
    onSecondary = onSecondaryMediumContrast,
    secondaryContainer = secondaryContainerMediumContrast,
    onSecondaryContainer = onSecondaryContainerMediumContrast,
    tertiary = tertiaryMediumContrast,
    onTertiary = onTertiaryMediumContrast,
    tertiaryContainer = tertiaryContainerMediumContrast,
    onTertiaryContainer = onTertiaryContainerMediumContrast,
    error = errorMediumContrast,
    onError = onErrorMediumContrast,
    errorContainer = errorContainerMediumContrast,
    onErrorContainer = onErrorContainerMediumContrast,
    background = backgroundMediumContrast,
    onBackground = onBackgroundMediumContrast,
    surface = surfaceMediumContrast,
    onSurface = onSurfaceMediumContrast,
    surfaceVariant = surfaceVariantMediumContrast,
    onSurfaceVariant = onSurfaceVariantMediumContrast,
    outline = outlineMediumContrast,
    outlineVariant = outlineVariantMediumContrast,
    scrim = scrimMediumContrast,
    inverseSurface = inverseSurfaceMediumContrast,
    inverseOnSurface = inverseOnSurfaceMediumContrast,
    inversePrimary = inversePrimaryMediumContrast,
    surfaceDim = surfaceDimMediumContrast,
    surfaceBright = surfaceBrightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestMediumContrast,
    surfaceContainerLow = surfaceContainerLowMediumContrast,
    surfaceContainer = surfaceContainerMediumContrast,
    surfaceContainerHigh = surfaceContainerHighMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestMediumContrast,
)

private val highContrastColorScheme = darkColorScheme(
    primary = primaryHighContrast,
    onPrimary = onPrimaryHighContrast,
    primaryContainer = primaryContainerHighContrast,
    onPrimaryContainer = onPrimaryContainerHighContrast,
    secondary = secondaryHighContrast,
    onSecondary = onSecondaryHighContrast,
    secondaryContainer = secondaryContainerHighContrast,
    onSecondaryContainer = onSecondaryContainerHighContrast,
    tertiary = tertiaryHighContrast,
    onTertiary = onTertiaryHighContrast,
    tertiaryContainer = tertiaryContainerHighContrast,
    onTertiaryContainer = onTertiaryContainerHighContrast,
    error = errorHighContrast,
    onError = onErrorHighContrast,
    errorContainer = errorContainerHighContrast,
    onErrorContainer = onErrorContainerHighContrast,
    background = backgroundHighContrast,
    onBackground = onBackgroundHighContrast,
    surface = surfaceHighContrast,
    onSurface = onSurfaceHighContrast,
    surfaceVariant = surfaceVariantHighContrast,
    onSurfaceVariant = onSurfaceVariantHighContrast,
    outline = outlineHighContrast,
    outlineVariant = outlineVariantHighContrast,
    scrim = scrimHighContrast,
    inverseSurface = inverseSurfaceHighContrast,
    inverseOnSurface = inverseOnSurfaceHighContrast,
    inversePrimary = inversePrimaryHighContrast,
    surfaceDim = surfaceDimHighContrast,
    surfaceBright = surfaceBrightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestHighContrast,
    surfaceContainerLow = surfaceContainerLowHighContrast,
    surfaceContainer = surfaceContainerHighContrast,
    surfaceContainerHigh = surfaceContainerHighHighContrast,
    surfaceContainerHighest = surfaceContainerHighestHighContrast,
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
fun WakeyWakeyTheme(
    content: @Composable() () -> Unit
) {
    val colorScheme = darkScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}