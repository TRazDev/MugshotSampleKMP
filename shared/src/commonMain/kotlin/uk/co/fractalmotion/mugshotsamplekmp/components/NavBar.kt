package uk.co.fractalmotion.mugshotsamplekmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotLayout
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotStroke
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme

data class NavItem(
    val glyph: String,
    val label: String,
    val accent: Color,
)

private val NavGlyphBadgeSize = 22.dp

/**
 * Bottom tab bar for switching between the demo screens - plain local selection state,
 * no navigation library, since this sample has no real back-stack/routing.
 */
@Composable
fun PixelBottomNav(
    items: List<NavItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MugshotTheme.colors

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surface),
        contentAlignment = Alignment.TopCenter,
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = MugshotLayout.maxContentWidth)
                .fillMaxWidth()
                .border(MugshotStroke.regular, colors.ink)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items.forEachIndexed { index, item ->
                val selected = index == selectedIndex
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSelect(index) }
                        .background(if (selected) colors.ink else colors.surface)
                        .padding(vertical = MugshotSpacing.sm),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        Modifier
                            .size(NavGlyphBadgeSize)
                            .background(if (selected) item.accent else Color.Transparent)
                            .border(MugshotStroke.hairline, if (selected) colors.ink else colors.hairline),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = item.glyph,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (selected) colors.ink else colors.inkMuted,
                        )
                    }
                    Spacer(Modifier.height(MugshotSpacing.xs))
                    Text(
                        text = item.label.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selected) colors.surface else colors.inkMuted,
                    )
                }
            }
        }
    }
}
