package uk.co.fractalmotion.mugshotsamplekmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.co.fractalmotion.mugshotsamplekmp.theme.FarmWindowSize
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotLayout
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotStroke
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

/**
 * Shared chrome for every module screen: dot-grid background, a header with the module
 * wordmark/title/status, responsive side padding, and a width cap so wide desktop/tablet
 * windows don't stretch cards edge to edge.
 */
@Composable
fun ModuleScreenScaffold(
    accent: ModuleAccent,
    title: String,
    subtitle: String,
    statusText: String,
    statusTone: StatusTone,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(FarmWindowSize) -> Unit,
) {
    FullBleedBackground(modifier) {
        BoxWithConstraints(Modifier.fillMaxSize()) {
            val windowSize = FarmWindowSize.from(maxWidth)
            val horizontalPadding = when (windowSize) {
                FarmWindowSize.Compact -> MugshotSpacing.lg
                FarmWindowSize.Medium -> MugshotSpacing.xxl
                FarmWindowSize.Expanded -> 48.dp
            }
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .widthIn(max = MugshotLayout.maxContentWidth)
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = horizontalPadding, vertical = MugshotSpacing.xl),
            ) {
                ModuleHeader(accent, title, subtitle, statusText, statusTone)
                Spacer(Modifier.height(MugshotSpacing.lg))
                content(windowSize)
                Spacer(Modifier.height(MugshotSpacing.xxl))
            }
        }
    }
}

@Composable
private fun ModuleHeader(
    accent: ModuleAccent,
    title: String,
    subtitle: String,
    statusText: String,
    statusTone: StatusTone,
) {
    val colors = MugshotTheme.colors
    Column {
        Text(
            text = "MUGSHOT FARM // ${accent.label}",
            style = MaterialTheme.typography.labelSmall,
            color = colors.inkMuted,
        )
        Spacer(Modifier.height(MugshotSpacing.xs))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Column(Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.headlineLarge, color = colors.ink)
                Spacer(Modifier.height(2.dp))
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = colors.inkMuted)
            }
            Spacer(Modifier.width(MugshotSpacing.md))
            StatusPill(text = statusText, tone = statusTone)
        }
        Spacer(Modifier.height(MugshotSpacing.md))
        Box(
            Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(accent.tone)
                .border(MugshotStroke.hairline, colors.ink),
        )
    }
}
