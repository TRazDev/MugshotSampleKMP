package uk.co.fractalmotion.mugshotsamplekmp.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Manual, KMP-common breakpoints (mirrors Material's window size classes) so screens can
 *  adapt across phone, tablet and desktop without pulling in a platform-specific library. */
enum class FarmWindowSize {
    Compact,
    Medium,
    Expanded;

    companion object {
        private val compactMaxWidth = 600.dp
        private val mediumMaxWidth = 900.dp

        fun from(width: Dp): FarmWindowSize = when {
            width < compactMaxWidth -> Compact
            width < mediumMaxWidth -> Medium
            else -> Expanded
        }
    }
}
