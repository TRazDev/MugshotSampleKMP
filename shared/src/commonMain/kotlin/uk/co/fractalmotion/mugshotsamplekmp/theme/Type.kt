package uk.co.fractalmotion.mugshotsamplekmp.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.sp
import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.press_start_2p
import mugshotsamplekmp.shared.generated.resources.space_mono_bold
import mugshotsamplekmp.shared.generated.resources.space_mono_regular
import org.jetbrains.compose.resources.Font

/**
 * Press Start 2P and Space Mono only cover Latin glyphs. Digits, ".", "%",
 * "°" etc. are always safe (used for every stat value regardless of locale),
 * but translated words fall back to the platform default family whenever the
 * active locale isn't Latin-script - otherwise Japanese copy renders as tofu.
 */
private val latinScriptLanguages = setOf("en", "it")

@Composable
private fun isLatinScriptLocale(): Boolean = Locale.current.language in latinScriptLanguages

@Composable
fun pixelFontFamily(): FontFamily = FontFamily(Font(Res.font.press_start_2p, weight = FontWeight.Normal))

@Composable
private fun monoFontFamily(): FontFamily = FontFamily(
    Font(Res.font.space_mono_regular, weight = FontWeight.Normal),
    Font(Res.font.space_mono_bold, weight = FontWeight.Bold),
)

/** Always-safe display face for numbers/units - digits render fine in every locale. */
@Composable
fun pixelDigitsFontFamily(): FontFamily = pixelFontFamily()

/** Word-bearing display face: pixel font for Latin locales, system default for CJK. */
@Composable
fun retroDisplayFontFamily(): FontFamily = if (isLatinScriptLocale()) pixelFontFamily() else FontFamily.Default

/** Word-bearing body face: retro monospace for Latin locales, system default for CJK. */
@Composable
fun retroBodyFontFamily(): FontFamily = if (isLatinScriptLocale()) monoFontFamily() else FontFamily.Default

@Composable
fun mugshotTypography(): Typography {
    val display = retroDisplayFontFamily()
    val body = retroBodyFontFamily()
    return Typography(
        headlineLarge = TextStyle(fontFamily = display, fontWeight = FontWeight.Bold, fontSize = 22.sp, lineHeight = 30.sp, letterSpacing = 0.5.sp),
        headlineMedium = TextStyle(fontFamily = display, fontWeight = FontWeight.Bold, fontSize = 18.sp, lineHeight = 26.sp, letterSpacing = 0.5.sp),
        titleLarge = TextStyle(fontFamily = display, fontWeight = FontWeight.Bold, fontSize = 15.sp, lineHeight = 22.sp, letterSpacing = 0.5.sp),
        titleMedium = TextStyle(fontFamily = body, fontWeight = FontWeight.Bold, fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = 1.sp),
        titleSmall = TextStyle(fontFamily = body, fontWeight = FontWeight.Bold, fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 1.2.sp),
        bodyLarge = TextStyle(fontFamily = body, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
        bodyMedium = TextStyle(fontFamily = body, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 18.sp),
        bodySmall = TextStyle(fontFamily = body, fontWeight = FontWeight.Normal, fontSize = 10.sp, lineHeight = 14.sp, letterSpacing = 0.4.sp),
        labelLarge = TextStyle(fontFamily = body, fontWeight = FontWeight.Bold, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 1.sp),
        labelMedium = TextStyle(fontFamily = body, fontWeight = FontWeight.Bold, fontSize = 10.sp, lineHeight = 14.sp, letterSpacing = 1.sp),
        labelSmall = TextStyle(fontFamily = body, fontWeight = FontWeight.Bold, fontSize = 9.sp, lineHeight = 12.sp, letterSpacing = 1.sp),
    )
}

/** Big numeric readout style - always uses the pixel font, safe in every locale. */
@Composable
fun statValueTextStyle(sizeSp: Int = 28): TextStyle = TextStyle(
    fontFamily = pixelDigitsFontFamily(),
    fontWeight = FontWeight.Normal,
    fontSize = sizeSp.sp,
    lineHeight = (sizeSp + 8).sp,
)
