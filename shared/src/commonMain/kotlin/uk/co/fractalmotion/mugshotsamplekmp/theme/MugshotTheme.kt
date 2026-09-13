package uk.co.fractalmotion.mugshotsamplekmp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class MugshotColorScheme(
    val isDark: Boolean,
    val background: Color,
    val surface: Color,
    val ink: Color,
    val inkMuted: Color,
    val hairline: Color,
)

private val LightMugshotColors = MugshotColorScheme(
    isDark = false,
    background = MugshotColors.Paper,
    surface = MugshotColors.BoneWhite,
    ink = MugshotColors.Ink,
    inkMuted = MugshotColors.InkSoft,
    hairline = MugshotColors.HairlineLight,
)

private val DarkMugshotColors = MugshotColorScheme(
    isDark = true,
    background = MugshotColors.VoidBlack,
    surface = MugshotColors.CoalSurface,
    ink = MugshotColors.BoneWhite,
    inkMuted = MugshotColors.BoneDim,
    hairline = MugshotColors.HairlineDark,
)

val LocalMugshotColors = staticCompositionLocalOf { LightMugshotColors }

object MugshotTheme {
    val colors: MugshotColorScheme
        @Composable get() = LocalMugshotColors.current
}

private val sharpCornerShape = RoundedCornerShape(0.dp)

private val sharpShapes = Shapes(
    extraSmall = sharpCornerShape,
    small = sharpCornerShape,
    medium = sharpCornerShape,
    large = sharpCornerShape,
    extraLarge = sharpCornerShape,
)

@Composable
fun MugshotAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val mugshotColors = if (darkTheme) DarkMugshotColors else LightMugshotColors
    // darkColorScheme/lightColorScheme aren't unified behind one call here on purpose - both take
    // 30+ optional params, and calling through a shared function reference would force positional
    // (unnamed) arguments, which is far easier to miswire than this small duplication.
    val materialScheme = if (darkTheme) {
        darkColorScheme(
            primary = mugshotColors.ink,
            onPrimary = mugshotColors.surface,
            background = mugshotColors.background,
            onBackground = mugshotColors.ink,
            surface = mugshotColors.surface,
            onSurface = mugshotColors.ink,
            outline = mugshotColors.hairline,
        )
    } else {
        lightColorScheme(
            primary = mugshotColors.ink,
            onPrimary = mugshotColors.surface,
            background = mugshotColors.background,
            onBackground = mugshotColors.ink,
            surface = mugshotColors.surface,
            onSurface = mugshotColors.ink,
            outline = mugshotColors.hairline,
        )
    }

    CompositionLocalProvider(
        LocalMugshotColors provides mugshotColors,
        // PixelCard etc. are plain Boxes, not Material Surfaces, so nothing else sets this -
        // without it, a Text() with no explicit color defaults to black in dark mode too.
        LocalContentColor provides mugshotColors.ink,
    ) {
        MaterialTheme(
            colorScheme = materialScheme,
            typography = mugshotTypography(),
            shapes = sharpShapes,
            content = content,
        )
    }
}
