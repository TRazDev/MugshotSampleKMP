package uk.co.fractalmotion.mugshotsamplekmp.screens.dashboard

import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.common_auto
import mugshotsamplekmp.shared.generated.resources.common_nominal
import mugshotsamplekmp.shared.generated.resources.common_warning
import mugshotsamplekmp.shared.generated.resources.fert_dosing_now
import mugshotsamplekmp.shared.generated.resources.nav_fertiliser
import mugshotsamplekmp.shared.generated.resources.nav_light
import mugshotsamplekmp.shared.generated.resources.nav_solar
import mugshotsamplekmp.shared.generated.resources.nav_water
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

/** Static sample data for previews - this sample has no real backend/data layer. */
object MockDashboardData {
    val vitals = DashboardVitals(
        temperatureC = 23.4,
        humidityPercent = 61,
        co2Ppm = 812,
        powerDrawWatts = 1840,
    )

    val moduleStatuses = listOf(
        ModuleStatus(
            accent = ModuleAccent.Light,
            labelRes = Res.string.nav_light,
            statusTextRes = Res.string.common_auto,
            statusTone = StatusTone.Nominal,
            headline = "6/6 zones on schedule",
            progressFraction = 1f,
        ),
        ModuleStatus(
            accent = ModuleAccent.Fertiliser,
            labelRes = Res.string.nav_fertiliser,
            statusTextRes = Res.string.fert_dosing_now,
            statusTone = StatusTone.Nominal,
            headline = "EC 1.8 - pH 6.1",
            progressFraction = 0.72f,
        ),
        ModuleStatus(
            accent = ModuleAccent.Water,
            labelRes = Res.string.nav_water,
            statusTextRes = Res.string.common_nominal,
            statusTone = StatusTone.Nominal,
            headline = "Reservoir at 68%",
            progressFraction = 0.68f,
        ),
        ModuleStatus(
            accent = ModuleAccent.Solar,
            labelRes = Res.string.nav_solar,
            statusTextRes = Res.string.common_warning,
            statusTone = StatusTone.Warning,
            headline = "Panel C output low",
            progressFraction = 0.41f,
        ),
    )

    val alerts = listOf(
        FarmAlert(message = "Solar panel C output 22% below array average", tone = StatusTone.Warning),
        FarmAlert(message = "Fertiliser tank B due for refill in 2 days", tone = StatusTone.Neutral),
    )

    val energyTodayKwh = 14.2
    val waterUsedTodayLiters = 186

    val default = DashboardUiState(
        vitals = vitals,
        modules = moduleStatuses,
        alerts = alerts,
        energyTodayKwh = energyTodayKwh,
        waterUsedTodayLiters = waterUsedTodayLiters,
    )
}
