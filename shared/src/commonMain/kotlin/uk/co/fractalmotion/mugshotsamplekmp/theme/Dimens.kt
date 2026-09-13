package uk.co.fractalmotion.mugshotsamplekmp.theme

import androidx.compose.ui.unit.dp

/** Everything is a multiple of a 4dp pixel-grid unit to keep the dot-matrix feel consistent. */
object MugshotSpacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 32.dp
}

object MugshotStroke {
    val hairline = 1.dp
    val regular = 2.dp
    val shadowOffset = 4.dp
}

object MugshotLayout {
    /** Caps content width on wide desktop/tablet windows - applied to both screen content and the bottom nav bar. */
    val maxContentWidth = 960.dp
}
