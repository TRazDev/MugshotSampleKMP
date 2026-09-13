package uk.co.fractalmotion.mugshotsamplekmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotStroke
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone
import uk.co.fractalmotion.mugshotsamplekmp.theme.statValueTextStyle

/** A bordered header line: a small square bullet, an uppercase title, and optional trailing content. */
@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = MugshotTheme.colors
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.width(8.dp).height(8.dp).background(colors.ink))
        Spacer(Modifier.width(MugshotSpacing.xs))
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.titleSmall,
            color = colors.ink,
            modifier = Modifier.weight(1f),
        )
        trailing?.invoke()
    }
}

/** Small bordered pill showing a status word next to a coloured dot, e.g. ONLINE / WARNING. */
@Composable
fun StatusPill(
    text: String,
    tone: StatusTone,
    modifier: Modifier = Modifier,
) {
    val colors = MugshotTheme.colors
    Row(
        modifier
            .border(MugshotStroke.hairline, colors.ink)
            .padding(horizontal = MugshotSpacing.sm, vertical = MugshotSpacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PixelDot(color = tone.tone, size = 7.dp)
        Spacer(Modifier.width(MugshotSpacing.xs))
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = colors.ink,
        )
    }
}

/** Big pixel-font numeric readout tile with an uppercase label and optional accent + unit. */
@Composable
fun StatTile(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    unit: String? = null,
    accent: Color? = null,
    valueSizeSp: Int = 26,
) {
    val colors = MugshotTheme.colors
    PixelCard(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (accent != null) {
                PixelDot(color = accent, size = 7.dp)
                Spacer(Modifier.width(MugshotSpacing.xs))
            }
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = colors.inkMuted,
            )
        }
        Spacer(Modifier.height(MugshotSpacing.xs))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(text = value, style = statValueTextStyle(valueSizeSp), color = colors.ink)
            if (unit != null) {
                Spacer(Modifier.width(4.dp))
                Text(
                    text = unit,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.inkMuted,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
        }
    }
}

/** Segmented block gauge (like an old LCD battery meter) representing a 0f..1f fraction. */
@Composable
fun SegmentedGauge(
    fraction: Float,
    modifier: Modifier = Modifier,
    segments: Int = 14,
    accent: Color? = null,
) {
    val colors = MugshotTheme.colors
    val filledCount = (fraction.coerceIn(0f, 1f) * segments).roundToInt()
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        repeat(segments) { index ->
            val filled = index < filledCount
            Box(
                Modifier
                    .weight(1f)
                    .height(16.dp)
                    .background(if (filled) (accent ?: colors.ink) else Color.Transparent)
                    .border(MugshotStroke.hairline, colors.ink),
            )
        }
    }
}

/** A thin labelled progress rule: label + value on one line, gauge underneath. */
@Composable
fun LabelledGauge(
    label: String,
    valueText: String,
    fraction: Float,
    modifier: Modifier = Modifier,
    accent: Color? = null,
) {
    val colors = MugshotTheme.colors
    Column(modifier) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label.uppercase(), style = MaterialTheme.typography.labelSmall, color = colors.inkMuted)
            Text(valueText, style = MaterialTheme.typography.labelSmall, color = colors.ink)
        }
        Spacer(Modifier.height(MugshotSpacing.xs))
        SegmentedGauge(fraction = fraction, accent = accent)
    }
}

/** Small label-over-value pair, used for secondary stats inside a card (e.g. ON time / OFF time). */
@Composable
fun InlineStat(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    val colors = MugshotTheme.colors
    Column(modifier, horizontalAlignment = horizontalAlignment) {
        Text(label.uppercase(), style = MaterialTheme.typography.labelSmall, color = colors.inkMuted)
        Spacer(Modifier.height(2.dp))
        Text(value, style = MaterialTheme.typography.titleSmall, color = colors.ink)
    }
}
