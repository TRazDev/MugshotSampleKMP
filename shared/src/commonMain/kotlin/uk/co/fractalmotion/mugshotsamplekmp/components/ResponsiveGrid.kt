package uk.co.fractalmotion.mugshotsamplekmp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing

/**
 * Lays [items] out in equal-width rows of [columns], padding the trailing row with
 * invisible spacers so the last tiles keep the same width as a full row.
 */
@Composable
fun <T> ResponsiveGrid(
    items: List<T>,
    columns: Int,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit,
) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(MugshotSpacing.md)) {
        items.chunked(columns).forEach { rowItems ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(MugshotSpacing.md)) {
                rowItems.forEach { item ->
                    Box(Modifier.weight(1f)) {
                        itemContent(item)
                    }
                }
                repeat(columns - rowItems.size) {
                    Box(Modifier.weight(1f)) {}
                }
            }
        }
    }
}
