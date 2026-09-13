package uk.co.fractalmotion.mugshotsamplekmp.screens.fertiliser

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
import mugshotsamplekmp.shared.generated.resources.common_auto
import mugshotsamplekmp.shared.generated.resources.fert_dosing_now
import mugshotsamplekmp.shared.generated.resources.fert_ec
import mugshotsamplekmp.shared.generated.resources.fert_ph
import mugshotsamplekmp.shared.generated.resources.fert_section_schedule
import mugshotsamplekmp.shared.generated.resources.fert_section_tanks
import mugshotsamplekmp.shared.generated.resources.fert_subtitle
import mugshotsamplekmp.shared.generated.resources.fert_tank_level
import mugshotsamplekmp.shared.generated.resources.fert_title
import org.jetbrains.compose.resources.stringResource
import uk.co.fractalmotion.mugshotsamplekmp.components.DotDivider
import uk.co.fractalmotion.mugshotsamplekmp.components.InlineStat
import uk.co.fractalmotion.mugshotsamplekmp.components.asFraction
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
fun FertiliserScreen(state: FertiliserUiState) {
    val (tanks, schedule) = state
    ModuleScreenScaffold(
        accent = ModuleAccent.Fertiliser,
        title = stringResource(Res.string.fert_title),
        subtitle = stringResource(Res.string.fert_subtitle),
        statusText = stringResource(Res.string.common_auto),
        statusTone = StatusTone.Nominal,
    ) {
        SectionHeader(title = stringResource(Res.string.fert_section_tanks))
        Spacer(Modifier.height(MugshotSpacing.sm))
        Column(verticalArrangement = Arrangement.spacedBy(MugshotSpacing.sm)) {
            tanks.forEach { tank -> NutrientTankCard(tank) }
        }

        Spacer(Modifier.height(MugshotSpacing.xl))
        SectionHeader(title = stringResource(Res.string.fert_section_schedule))
        Spacer(Modifier.height(MugshotSpacing.sm))
        PixelCard(modifier = Modifier.fillMaxWidth()) {
            schedule.forEachIndexed { index, dose ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PixelDot(color = ModuleAccent.Fertiliser.tone)
                        Spacer(Modifier.width(MugshotSpacing.sm))
                        Text(dose.time, style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
                    }
                    Text(dose.tankName, style = MaterialTheme.typography.bodySmall, color = MugshotTheme.colors.inkMuted)
                    Text("${dose.amountMl} mL", style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
                }
                if (index != schedule.lastIndex) {
                    Spacer(Modifier.height(MugshotSpacing.sm))
                    DotDivider()
                    Spacer(Modifier.height(MugshotSpacing.sm))
                }
            }
        }
    }
}

@Composable
private fun NutrientTankCard(tank: NutrientTank) {
    PixelCard(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(tank.name, style = MaterialTheme.typography.titleSmall, color = MugshotTheme.colors.ink)
            StatusPill(text = stringResource(Res.string.fert_dosing_now), tone = StatusTone.Nominal)
        }
        Spacer(Modifier.height(MugshotSpacing.sm))
        LabelledGauge(
            label = stringResource(Res.string.fert_tank_level),
            valueText = "${tank.levelPercent}%",
            fraction = tank.levelPercent.asFraction(),
            accent = ModuleAccent.Fertiliser.tone,
        )
        Spacer(Modifier.height(MugshotSpacing.sm))
        DotDivider()
        Spacer(Modifier.height(MugshotSpacing.sm))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            InlineStat(stringResource(Res.string.fert_ec), "${tank.ec}")
            InlineStat(stringResource(Res.string.fert_ph), "${tank.ph}", horizontalAlignment = Alignment.End)
        }
    }
}

@Mugshot
@MugshotLightDark
@Preview
@Composable
internal fun FertiliserScreenPreview() {
    MugshotAppTheme { FertiliserScreen(state = MockFertiliserData.default) }
}
