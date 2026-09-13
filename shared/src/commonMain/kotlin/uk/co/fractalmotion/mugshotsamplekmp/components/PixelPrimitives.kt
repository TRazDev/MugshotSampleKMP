package uk.co.fractalmotion.mugshotsamplekmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotStroke
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme

/**
 * A card with a hard offset shadow (no blur) and a sharp ink border -
 * the signature neo-brutalist / dot-matrix silhouette used everywhere.
 */
@Composable
fun PixelCard(
    modifier: Modifier = Modifier,
    fill: Color? = null,
    contentPadding: PaddingValues = PaddingValues(MugshotSpacing.md),
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = MugshotTheme.colors
    Box(modifier = modifier) {
        Box(
            Modifier
                .matchParentSize()
                .offset(x = MugshotStroke.shadowOffset, y = MugshotStroke.shadowOffset)
                .background(colors.ink),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(fill ?: colors.surface)
                .border(MugshotStroke.regular, colors.ink)
                .padding(contentPadding),
            content = content,
        )
    }
}

/** Dashed/dotted rule reminiscent of a dot-matrix printout. */
@Composable
fun DotDivider(
    modifier: Modifier = Modifier,
    color: Color = MugshotTheme.colors.hairline,
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(6.dp)
            .drawBehind {
                val dot = 2.dp.toPx()
                val gap = 4.dp.toPx()
                var x = 0f
                val y = size.height / 2f - dot / 2f
                while (x < size.width) {
                    drawRect(color, topLeft = Offset(x, y), size = Size(dot, dot))
                    x += dot + gap
                }
            },
    )
}

/** Subtle background dot grid, like the texture on a Nothing Phone back glass. */
fun Modifier.dotGridBackground(color: Color, spacing: Dp = 18.dp, dotSize: Dp = 1.4.dp): Modifier =
    this.drawBehind {
        val spacingPx = spacing.toPx()
        val dotPx = dotSize.toPx()
        var y = spacingPx / 2f
        while (y < size.height) {
            var x = spacingPx / 2f
            while (x < size.width) {
                drawRect(color, topLeft = Offset(x, y), size = Size(dotPx, dotPx))
                x += spacingPx
            }
            y += spacingPx
        }
    }

/** Small square swatch used as an accent/status marker next to labels. */
@Composable
fun PixelDot(
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp = 8.dp,
    bordered: Boolean = true,
) {
    val colors = MugshotTheme.colors
    Box(
        modifier
            .size(size)
            .background(color)
            .let { if (bordered) it.border(MugshotStroke.hairline, colors.ink) else it },
    )
}

@Composable
fun FullBleedBackground(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    val colors = MugshotTheme.colors
    Box(
        modifier
            .fillMaxSize()
            .background(colors.background)
            .dotGridBackground(colors.hairline.copy(alpha = if (colors.isDark) 0.5f else 0.7f)),
        content = content,
    )
}
