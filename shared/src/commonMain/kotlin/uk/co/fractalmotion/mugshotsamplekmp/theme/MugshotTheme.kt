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
    val surfaceAlt: Color,
    val ink: Color,
    val inkMuted: Color,
    val hairline: Color,
    val inverseSurface: Color,
    val inverseInk: Color,
)

private val LightMugshotColors = MugshotColorScheme(
    isDark = false,
    background = MugshotColors.Paper,
    surface = MugshotColors.BoneWhite,
    surfaceAlt = MugshotColors.PaperDim,
    ink = MugshotColors.Ink,
    inkMuted = MugshotColors.InkSoft,
    hairline = MugshotColors.HairlineLight,
    inverseSurface = MugshotColors.VoidBlack,
    inverseInk = MugshotColors.BoneWhite,
)

private val DarkMugshotColors = MugshotColorScheme(
    isDark = true,
    background = MugshotColors.VoidBlack,
    surface = MugshotColors.CoalSurface,
    surfaceAlt = MugshotColors.CoalSurfaceAlt,
    ink = MugshotColors.BoneWhite,
    inkMuted = MugshotColors.BoneDim,
    hairline = MugshotColors.HairlineDark,
    inverseSurface = MugshotColors.Paper,
    inverseInk = MugshotColors.Ink,
)

val LocalMugshotColors = staticCompositionLocalOf { LightMugshotColors }

object MugshotTheme {
    val colors: MugshotColorScheme
        @Composable get() = LocalMugshotColors.current
}

private val Sharp = RoundedCornerShape(0.dp)

private val SharpShapes = Shapes(
    extraSmall = Sharp,
    small = Sharp,
    medium = Sharp,
    large = Sharp,
    extraLarge = Sharp,
)

@Composable
fun MugshotAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val mugshotColors = if (darkTheme) DarkMugshotColors else LightMugshotColors
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
            shapes = SharpShapes,
            content = content,
        )
    }
}
