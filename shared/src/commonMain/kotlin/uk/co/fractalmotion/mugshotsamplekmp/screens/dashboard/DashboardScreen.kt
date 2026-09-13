package uk.co.fractalmotion.mugshotsamplekmp.screens.dashboard

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
import uk.co.fractalmotion.mugshot.annotations.Mugshot
import uk.co.fractalmotion.mugshot.annotations.MugshotLightDark
import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.dashboard_alert_title
import mugshotsamplekmp.shared.generated.resources.dashboard_energy_today
import mugshotsamplekmp.shared.generated.resources.dashboard_no_alerts
import mugshotsamplekmp.shared.generated.resources.dashboard_section_modules
import mugshotsamplekmp.shared.generated.resources.dashboard_section_stats
import mugshotsamplekmp.shared.generated.resources.dashboard_stat_co2
import mugshotsamplekmp.shared.generated.resources.dashboard_stat_humidity
import mugshotsamplekmp.shared.generated.resources.dashboard_stat_power
import mugshotsamplekmp.shared.generated.resources.dashboard_stat_temperature
import mugshotsamplekmp.shared.generated.resources.dashboard_subtitle
import mugshotsamplekmp.shared.generated.resources.dashboard_title
import mugshotsamplekmp.shared.generated.resources.dashboard_water_used_today
import mugshotsamplekmp.shared.generated.resources.common_online
import org.jetbrains.compose.resources.stringResource
import uk.co.fractalmotion.mugshotsamplekmp.components.DotDivider
import uk.co.fractalmotion.mugshotsamplekmp.components.ModuleScreenScaffold
import uk.co.fractalmotion.mugshotsamplekmp.components.PixelCard
import uk.co.fractalmotion.mugshotsamplekmp.components.PixelDot
import uk.co.fractalmotion.mugshotsamplekmp.components.ResponsiveGrid
import uk.co.fractalmotion.mugshotsamplekmp.components.SectionHeader
import uk.co.fractalmotion.mugshotsamplekmp.components.SegmentedGauge
import uk.co.fractalmotion.mugshotsamplekmp.components.StatTile
import uk.co.fractalmotion.mugshotsamplekmp.components.StatusPill
import uk.co.fractalmotion.mugshotsamplekmp.theme.FarmWindowSize
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotAppTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotSpacing
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotTheme
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

@Composable
fun DashboardScreen(state: DashboardUiState) {
    val (vitals, modules, alerts, energyTodayKwh, waterUsedTodayLiters) = state
    ModuleScreenScaffold(
        accent = ModuleAccent.Dashboard,
        title = stringResource(Res.string.dashboard_title),
        subtitle = stringResource(Res.string.dashboard_subtitle),
        statusText = stringResource(Res.string.common_online),
        statusTone = StatusTone.Nominal,
    ) { windowSize ->
        val statColumns = if (windowSize == FarmWindowSize.Compact) 2 else 4

        SectionHeader(title = stringResource(Res.string.dashboard_section_stats))
        Spacer(Modifier.height(MugshotSpacing.sm))
        ResponsiveGrid(
            items = listOf(
                Triple(stringResource(Res.string.dashboard_stat_temperature), "${vitals.temperatureC}", "°C"),
                Triple(stringResource(Res.string.dashboard_stat_humidity), "${vitals.humidityPercent}", "%"),
                Triple(stringResource(Res.string.dashboard_stat_co2), "${vitals.co2Ppm}", "PPM"),
                Triple(stringResource(Res.string.dashboard_stat_power), "${vitals.powerDrawWatts}", "W"),
            ),
            columns = statColumns,
        ) { (label, value, unit) ->
            StatTile(label = label, value = value, unit = unit)
        }

        Spacer(Modifier.height(MugshotSpacing.xl))
        SectionHeader(title = stringResource(Res.string.dashboard_section_modules))
        Spacer(Modifier.height(MugshotSpacing.sm))
        Column(verticalArrangement = Arrangement.spacedBy(MugshotSpacing.sm)) {
            modules.forEach { module -> ModuleStatusRow(module) }
        }

        Spacer(Modifier.height(MugshotSpacing.xl))
        SectionHeader(title = stringResource(Res.string.dashboard_alert_title))
        Spacer(Modifier.height(MugshotSpacing.sm))
        PixelCard(modifier = Modifier.fillMaxWidth()) {
            if (alerts.isEmpty()) {
                Text(
                    text = stringResource(Res.string.dashboard_no_alerts),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MugshotTheme.colors.inkMuted,
                )
            } else {
                alerts.forEachIndexed { index, alert ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PixelDot(color = alert.tone.tone)
                        Spacer(Modifier.width(MugshotSpacing.sm))
                        Text(
                            text = alert.message,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MugshotTheme.colors.ink,
                        )
                    }
                    if (index != alerts.lastIndex) {
                        Spacer(Modifier.height(MugshotSpacing.sm))
                        DotDivider()
                        Spacer(Modifier.height(MugshotSpacing.sm))
                    }
                }
            }
        }

        Spacer(Modifier.height(MugshotSpacing.xl))
        ResponsiveGrid(
            items = listOf(
                Triple(stringResource(Res.string.dashboard_energy_today), "$energyTodayKwh", "KWH"),
                Triple(stringResource(Res.string.dashboard_water_used_today), "$waterUsedTodayLiters", "L"),
            ),
            columns = if (windowSize == FarmWindowSize.Compact) 1 else 2,
        ) { (label, value, unit) ->
            StatTile(label = label, value = value, unit = unit, accent = ModuleAccent.Dashboard.tone)
        }
    }
}

@Composable
private fun ModuleStatusRow(module: ModuleStatus) {
    PixelCard(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PixelDot(color = module.accent.tone)
                Spacer(Modifier.width(MugshotSpacing.sm))
                Text(stringResource(module.labelRes).uppercase(), style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
            }
            StatusPill(text = stringResource(module.statusTextRes), tone = module.statusTone)
        }
        Spacer(Modifier.height(MugshotSpacing.xs))
        Text(module.headline, style = MaterialTheme.typography.bodySmall, color = MugshotTheme.colors.inkMuted)
        Spacer(Modifier.height(MugshotSpacing.sm))
        SegmentedGauge(fraction = module.progressFraction, accent = module.accent.tone)
    }
}

@Mugshot
@MugshotLightDark
@Preview
@Composable
internal fun DashboardScreenPreview() {
    MugshotAppTheme { DashboardScreen(state = MockDashboardData.default) }
}
