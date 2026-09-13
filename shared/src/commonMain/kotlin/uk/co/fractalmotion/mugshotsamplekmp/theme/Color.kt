package uk.co.fractalmotion.mugshotsamplekmp.theme

import androidx.compose.ui.graphics.Color

/**
 * Neo 8-bit / dot-matrix palette: near-black and near-white as the only
 * "loud" colours, with a handful of pale accents reserved for module
 * identity and status - deliberately pure black on pure white so it reads
 * closer to a dot-matrix receipt than a typical app screen.
 */
object MugshotColors {
    val Ink = Color(0xFF111110)
    val InkSoft = Color(0xFF33322F)
    val Paper = Color(0xFFF7F5EF)

    val VoidBlack = Color(0xFF0A0A09)
    val CoalSurface = Color(0xFF191917)
    val BoneWhite = Color(0xFFF2F0E8)
    val BoneDim = Color(0xFFB9B6A8)

    val HairlineLight = Color(0xFFC9C6B9)
    val HairlineDark = Color(0xFF44443F)

    // Pale module accents - kept low-saturation so the black/white grid stays dominant.
    val PaleAmber = Color(0xFFE8D48A)
    val PaleGreen = Color(0xFFAECDAA)
    val PaleBlue = Color(0xFFA9C6DA)
    val PaleOrange = Color(0xFFE3B183)
    val PaleRed = Color(0xFFD79A93)
    val PaleTeal = Color(0xFFA0C9BE)
}

enum class ModuleAccent(val label: String, val tone: Color) {
    Dashboard(label = "DASH", tone = MugshotColors.PaleTeal),
    Light(label = "LIGHT", tone = MugshotColors.PaleAmber),
    Fertiliser(label = "FERT", tone = MugshotColors.PaleGreen),
    Water(label = "WATER", tone = MugshotColors.PaleBlue),
    Solar(label = "SOLAR", tone = MugshotColors.PaleOrange),
}

enum class StatusTone(val tone: Color) {
    Nominal(MugshotColors.PaleGreen),
    Warning(MugshotColors.PaleAmber),
    Critical(MugshotColors.PaleRed),
    Neutral(MugshotColors.BoneDim),
}
