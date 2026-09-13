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
                .background(colors.surface)
                .border(MugshotStroke.regular, colors.ink)
                .padding(contentPadding),
            content = content,
        )
    }
}

private val DotDividerHeight = 6.dp
private val DotDividerDotSize = 2.dp
private val DotDividerGap = 4.dp

/** Dashed/dotted rule reminiscent of a dot-matrix printout. */
@Composable
fun DotDivider(
    modifier: Modifier = Modifier,
    color: Color = MugshotTheme.colors.hairline,
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(DotDividerHeight)
            .drawBehind {
                val dotSizePx = DotDividerDotSize.toPx()
                val gapPx = DotDividerGap.toPx()
                var x = 0f
                val y = size.height / 2f - dotSizePx / 2f
                while (x < size.width) {
                    drawRect(color, topLeft = Offset(x, y), size = Size(dotSizePx, dotSizePx))
                    x += dotSizePx + gapPx
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

private val PixelDotDefaultSize = 7.dp

/** Small bordered square swatch used as an accent/status marker next to labels. */
@Composable
fun PixelDot(
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp = PixelDotDefaultSize,
) {
    val colors = MugshotTheme.colors
    Box(
        modifier
            .size(size)
            .background(color)
            .border(MugshotStroke.hairline, colors.ink),
    )
}

// The dot grid reads as a faint texture rather than a pattern - slightly stronger in light mode
// since the same alpha looks fainter against a dark background.
private const val DotGridAlphaDark = 0.5f
private const val DotGridAlphaLight = 0.7f

@Composable
fun FullBleedBackground(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    val colors = MugshotTheme.colors
    val dotGridAlpha = if (colors.isDark) DotGridAlphaDark else DotGridAlphaLight
    Box(
        modifier
            .fillMaxSize()
            .background(colors.background)
            .dotGridBackground(colors.hairline.copy(alpha = dotGridAlpha)),
        content = content,
    )
}
