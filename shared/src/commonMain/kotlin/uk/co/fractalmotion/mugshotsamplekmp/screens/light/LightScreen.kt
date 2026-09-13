package uk.co.fractalmotion.mugshotsamplekmp.screens.light

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.light_daily_hours
import mugshotsamplekmp.shared.generated.resources.light_intensity
import mugshotsamplekmp.shared.generated.resources.light_off_time
import mugshotsamplekmp.shared.generated.resources.light_on_time
import mugshotsamplekmp.shared.generated.resources.light_section_zones
import mugshotsamplekmp.shared.generated.resources.light_subtitle
import mugshotsamplekmp.shared.generated.resources.light_title
import mugshotsamplekmp.shared.generated.resources.light_zone_status_fault
import mugshotsamplekmp.shared.generated.resources.light_zone_status_off
import mugshotsamplekmp.shared.generated.resources.light_zone_status_on
import org.jetbrains.compose.resources.stringResource
import uk.co.fractalmotion.mugshotsamplekmp.components.DotDivider
import uk.co.fractalmotion.mugshotsamplekmp.components.InlineStat
import uk.co.fractalmotion.mugshotsamplekmp.components.LabelledGauge
import uk.co.fractalmotion.mugshotsamplekmp.components.ModuleScreenScaffold
import uk.co.fractalmotion.mugshotsamplekmp.components.PixelCard
import uk.co.fractalmotion.mugshotsamplekmp.components.PixelDot
import uk.co.fractalmotion.mugshotsamplekmp.components.SectionHeader
import uk.co.fractalmotion.mugshotsamplekmp.components.StatusPill
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotAppTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

@Composable
fun LightScreen(state: LightUiState) {
    ModuleScreenScaffold(
        accent = ModuleAccent.Light,
        title = stringResource(Res.string.light_title),
        subtitle = stringResource(Res.string.light_subtitle),
        statusText = stringResource(state.headerStatusTextRes),
        statusTone = state.headerStatusTone,
    ) {
        SectionHeader(title = stringResource(Res.string.light_section_zones))
        Spacer(Modifier.height(MugshotSpacing.sm))
        Column(verticalArrangement = Arrangement.spacedBy(MugshotSpacing.sm)) {
            state.zones.forEach { zone -> LightZoneCard(zone) }
        }
    }
}

@Composable
private fun LightZoneCard(zone: LightZone) {
    val hasFault = zone.faultMessage != null
    PixelCard(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(zone.name, style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
            StatusPill(
                text = when {
                    hasFault -> stringResource(Res.string.light_zone_status_fault)
                    zone.isOn -> stringResource(Res.string.light_zone_status_on)
                    else -> stringResource(Res.string.light_zone_status_off)
                },
                tone = when {
                    hasFault -> StatusTone.Critical
                    zone.isOn -> StatusTone.Nominal
                    else -> StatusTone.Neutral
                },
            )
        }
        if (hasFault) {
            Spacer(Modifier.height(MugshotSpacing.sm))
            Row(verticalAlignment = Alignment.CenterVertically) {
                PixelDot(color = StatusTone.Critical.tone, size = 7.dp)
                Spacer(Modifier.width(MugshotSpacing.sm))
                Text(
                    text = zone.faultMessage.orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MugshotTheme.colors.ink,
                )
            }
        }
        Spacer(Modifier.height(MugshotSpacing.sm))
        LabelledGauge(
            label = stringResource(Res.string.light_intensity),
            valueText = "${zone.intensityPercent}%",
            fraction = zone.intensityPercent / 100f,
            accent = if (hasFault) StatusTone.Critical.tone else ModuleAccent.Light.tone,
        )
        Spacer(Modifier.height(MugshotSpacing.sm))
        DotDivider()
        Spacer(Modifier.height(MugshotSpacing.sm))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            InlineStat(stringResource(Res.string.light_on_time), zone.onTime)
            InlineStat(stringResource(Res.string.light_off_time), zone.offTime, horizontalAlignment = Alignment.CenterHorizontally)
            InlineStat(stringResource(Res.string.light_daily_hours), "${zone.dailyHours}h", horizontalAlignment = Alignment.End)
        }
    }
}

/** Feeds the Light screen preview two states - nominal operation and a zone in fault - side by side. */
class LightUiStatePreviewParameterProvider : PreviewParameterProvider<LightUiState> {
    override val values = sequenceOf(MockLightData.nominal, MockLightData.critical)
}

@Preview
@Composable
private fun LightScreenPreview(@PreviewParameter(LightUiStatePreviewParameterProvider::class) state: LightUiState) {
    MugshotAppTheme { LightScreen(state) }
}
