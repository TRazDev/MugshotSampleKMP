package uk.co.fractalmotion.mugshotsamplekmp.screens.water

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.co.fractalmotion.mugshot.annotations.Mugshot
import uk.co.fractalmotion.mugshot.annotations.MugshotLightDark
import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.common_active
import mugshotsamplekmp.shared.generated.resources.common_idle
import mugshotsamplekmp.shared.generated.resources.common_nominal
import mugshotsamplekmp.shared.generated.resources.water_flow_rate
import mugshotsamplekmp.shared.generated.resources.water_next_cycle
import mugshotsamplekmp.shared.generated.resources.water_ph
import mugshotsamplekmp.shared.generated.resources.water_pump_status
import mugshotsamplekmp.shared.generated.resources.water_reservoir_level
import mugshotsamplekmp.shared.generated.resources.water_section_lines
import mugshotsamplekmp.shared.generated.resources.water_subtitle
import mugshotsamplekmp.shared.generated.resources.water_temperature
import mugshotsamplekmp.shared.generated.resources.water_title
import org.jetbrains.compose.resources.stringResource
import uk.co.fractalmotion.mugshot.annotations.MugshotDevices
import uk.co.fractalmotion.mugshot.annotations.MugshotLocales
import uk.co.fractalmotion.mugshotsamplekmp.components.InlineStat
import uk.co.fractalmotion.mugshotsamplekmp.components.asFraction
import uk.co.fractalmotion.mugshotsamplekmp.components.LabelledGauge
import uk.co.fractalmotion.mugshotsamplekmp.components.ModuleScreenScaffold
import uk.co.fractalmotion.mugshotsamplekmp.components.PixelCard
import uk.co.fractalmotion.mugshotsamplekmp.components.ResponsiveGrid
import uk.co.fractalmotion.mugshotsamplekmp.components.SectionHeader
import uk.co.fractalmotion.mugshotsamplekmp.components.StatTile
import uk.co.fractalmotion.mugshotsamplekmp.components.StatusPill
import uk.co.fractalmotion.mugshotsamplekmp.theme.FarmWindowSize
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotAppTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

@Composable
fun WaterScreen(state: WaterUiState) {
    val (reservoirLevelPercent, flowRateLpm, waterTemperatureC, waterPh, pumpActive, nextCycleTime, lines) = state
    ModuleScreenScaffold(
        accent = ModuleAccent.Water,
        title = stringResource(Res.string.water_title),
        subtitle = stringResource(Res.string.water_subtitle),
        statusText = stringResource(Res.string.common_nominal),
        statusTone = StatusTone.Nominal,
    ) { windowSize ->
        PixelCard(modifier = Modifier.fillMaxWidth()) {
            LabelledGauge(
                label = stringResource(Res.string.water_reservoir_level),
                valueText = "$reservoirLevelPercent%",
                fraction = reservoirLevelPercent.asFraction(),
                accent = ModuleAccent.Water.tone,
            )
        }

        Spacer(Modifier.height(MugshotSpacing.md))
        PixelCard(modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(
                        text = stringResource(Res.string.water_pump_status).uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MugshotTheme.colors.inkMuted,
                    )
                    Spacer(Modifier.height(MugshotSpacing.xs))
                    StatusPill(
                        text = stringResource(if (pumpActive) Res.string.common_active else Res.string.common_idle),
                        tone = if (pumpActive) StatusTone.Nominal else StatusTone.Neutral,
                    )
                }
                InlineStat(
                    label = stringResource(Res.string.water_next_cycle),
                    value = nextCycleTime,
                    horizontalAlignment = Alignment.End,
                )
            }
        }

        Spacer(Modifier.height(MugshotSpacing.lg))
        ResponsiveGrid(
            items = listOf(
                Triple(stringResource(Res.string.water_flow_rate), "$flowRateLpm", "L/MIN"),
                Triple(stringResource(Res.string.water_temperature), "$waterTemperatureC", "°C"),
                Triple(stringResource(Res.string.water_ph), "$waterPh", ""),
            ),
            columns = if (windowSize == FarmWindowSize.Compact) 1 else 3,
        ) { (label, value, unit) ->
            StatTile(label = label, value = value, unit = unit.ifEmpty { null })
        }

        Spacer(Modifier.height(MugshotSpacing.xl))
        SectionHeader(title = stringResource(Res.string.water_section_lines))
        Spacer(Modifier.height(MugshotSpacing.sm))
        Column(verticalArrangement = Arrangement.spacedBy(MugshotSpacing.sm)) {
            lines.forEach { line -> IrrigationLineRow(line) }
        }
    }
}

@Composable
private fun IrrigationLineRow(line: IrrigationLine) {
    PixelCard(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(line.name, style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
            StatusPill(
                text = stringResource(if (line.isActive) Res.string.common_active else Res.string.common_idle),
                tone = if (line.isActive) StatusTone.Nominal else StatusTone.Neutral,
            )
        }
        Spacer(Modifier.height(MugshotSpacing.sm))
        InlineStat(stringResource(Res.string.water_flow_rate), "${line.flowLpm} L/min")
    }
}

@Mugshot
@MugshotLightDark
@MugshotDevices
@MugshotLocales("en", "it", "ja")
@Preview
@Composable
internal fun WaterScreenPreview() {
    MugshotAppTheme { WaterScreen(state = MockWaterData.default) }
}
