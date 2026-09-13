package uk.co.fractalmotion.mugshotsamplekmp.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Manual, KMP-common breakpoints (mirrors Material's window size classes) so screens can
 *  adapt across phone, tablet and desktop without pulling in a platform-specific library. */
enum class FarmWindowSize {
    Compact,
    Medium,
    Expanded;

    val isCompact get() = this == Compact

    companion object {
        fun from(width: Dp): FarmWindowSize = when {
            width < 600.dp -> Compact
            width < 900.dp -> Medium
            else -> Expanded
        }
    }
}
