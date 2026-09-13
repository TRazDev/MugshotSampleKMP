package uk.co.fractalmotion.mugshotsamplekmp.screens.solar

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.common_online
import mugshotsamplekmp.shared.generated.resources.solar_battery
import mugshotsamplekmp.shared.generated.resources.solar_efficiency
import mugshotsamplekmp.shared.generated.resources.solar_grid_draw
import mugshotsamplekmp.shared.generated.resources.solar_grid_feed
import mugshotsamplekmp.shared.generated.resources.solar_production
import mugshotsamplekmp.shared.generated.resources.solar_section_panels
import mugshotsamplekmp.shared.generated.resources.solar_subtitle
import mugshotsamplekmp.shared.generated.resources.solar_title
import mugshotsamplekmp.shared.generated.resources.solar_today_total
import org.jetbrains.compose.resources.stringResource
import uk.co.fractalmotion.mugshotsamplekmp.components.asFraction
import uk.co.fractalmotion.mugshotsamplekmp.components.LabelledGauge
import uk.co.fractalmotion.mugshotsamplekmp.components.ModuleScreenScaffold
import uk.co.fractalmotion.mugshotsamplekmp.components.PixelCard
import uk.co.fractalmotion.mugshotsamplekmp.components.ResponsiveGrid
import uk.co.fractalmotion.mugshotsamplekmp.components.SectionHeader
import uk.co.fractalmotion.mugshotsamplekmp.components.StatTile
import uk.co.fractalmotion.mugshotsamplekmp.theme.FarmWindowSize
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotAppTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone
import uk.co.fractalmotion.mugshotsamplekmp.theme.statValueTextStyle

private const val ProductionValueFontSizeSp = 40
private val ProductionUnitBaselineGap = 6.dp

@Composable
fun SolarScreen(state: SolarUiState) {
    val (productionWatts, batteryPercent, gridFeedWatts, gridDrawWatts, todayTotalKwh, efficiencyPercent, panels) = state
    ModuleScreenScaffold(
        accent = ModuleAccent.Solar,
        title = stringResource(Res.string.solar_title),
        subtitle = stringResource(Res.string.solar_subtitle),
        statusText = stringResource(Res.string.common_online),
        statusTone = StatusTone.Nominal,
    ) { windowSize ->
        PixelCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(Res.string.solar_production).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MugshotTheme.colors.inkMuted,
            )
            Spacer(Modifier.height(MugshotSpacing.xs))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$productionWatts",
                    style = statValueTextStyle(ProductionValueFontSizeSp),
                    color = MugshotTheme.colors.ink,
                )
                Spacer(Modifier.width(MugshotSpacing.xs))
                Text(
                    text = "W",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MugshotTheme.colors.inkMuted,
                    modifier = Modifier.padding(bottom = ProductionUnitBaselineGap),
                )
            }
            Spacer(Modifier.height(MugshotSpacing.md))
            LabelledGauge(
                label = stringResource(Res.string.solar_battery),
                valueText = "$batteryPercent%",
                fraction = batteryPercent.asFraction(),
                accent = ModuleAccent.Solar.tone,
            )
        }

        Spacer(Modifier.height(MugshotSpacing.lg))
        ResponsiveGrid(
            items = listOf(
                Triple(stringResource(Res.string.solar_grid_feed), "$gridFeedWatts", "W"),
                Triple(stringResource(Res.string.solar_grid_draw), "$gridDrawWatts", "W"),
                Triple(stringResource(Res.string.solar_today_total), "$todayTotalKwh", "KWH"),
                Triple(stringResource(Res.string.solar_efficiency), "$efficiencyPercent", "%"),
            ),
            columns = if (windowSize == FarmWindowSize.Compact) 2 else 4,
        ) { (label, value, unit) ->
            StatTile(label = label, value = value, unit = unit)
        }

        Spacer(Modifier.height(MugshotSpacing.xl))
        SectionHeader(title = stringResource(Res.string.solar_section_panels))
        Spacer(Modifier.height(MugshotSpacing.sm))
        Column(verticalArrangement = Arrangement.spacedBy(MugshotSpacing.sm)) {
            panels.forEach { panel -> SolarPanelRow(panel) }
        }
    }
}

@Composable
private fun SolarPanelRow(panel: SolarPanel) {
    PixelCard(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(panel.name, style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
            Text("${panel.outputWatts} W", style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
        }
        Spacer(Modifier.height(MugshotSpacing.sm))
        LabelledGauge(
            label = stringResource(Res.string.solar_efficiency),
            valueText = "${panel.efficiencyPercent}%",
            fraction = panel.efficiencyPercent.asFraction(),
            accent = ModuleAccent.Solar.tone,
        )
    }
}

@Preview
@Composable
private fun SolarScreenPreview() {
    MugshotAppTheme { SolarScreen(state = MockSolarData.default) }
}
