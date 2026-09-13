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
        ModuleStatus(Res.string.nav_light, Res.string.common_auto, StatusTone.Nominal, "6/6 zones on schedule", 1f),
        ModuleStatus(Res.string.nav_fertiliser, Res.string.fert_dosing_now, StatusTone.Nominal, "EC 1.8 - pH 6.1", 0.72f),
        ModuleStatus(Res.string.nav_water, Res.string.common_nominal, StatusTone.Nominal, "Reservoir at 68%", 0.68f),
        ModuleStatus(Res.string.nav_solar, Res.string.common_warning, StatusTone.Warning, "Panel C output low", 0.41f),
    )

    val alerts = listOf(
        FarmAlert("Solar panel C output 22% below array average", StatusTone.Warning),
        FarmAlert("Fertiliser tank B due for refill in 2 days", StatusTone.Neutral),
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
