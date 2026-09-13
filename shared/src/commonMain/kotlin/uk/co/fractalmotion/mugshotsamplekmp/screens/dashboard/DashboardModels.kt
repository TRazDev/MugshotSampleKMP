package uk.co.fractalmotion.mugshotsamplekmp.screens.dashboard

import org.jetbrains.compose.resources.StringResource
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

data class DashboardVitals(
    val temperatureC: Double,
    val humidityPercent: Int,
    val co2Ppm: Int,
    val powerDrawWatts: Int,
)

/**
 * [labelRes] and [statusTextRes] are string resources (not raw strings) so the module name and
 * status word on the dashboard stay translated; [headline] is free-form mock telemetry text and
 * is left as English sample content, the same way [FarmAlert.message] is.
 */
data class ModuleStatus(
    val accent: ModuleAccent,
    val labelRes: StringResource,
    val statusTextRes: StringResource,
    val statusTone: StatusTone,
    val headline: String,
    val progressFraction: Float,
)

data class FarmAlert(
    val message: String,
    val tone: StatusTone,
)

data class DashboardUiState(
    val vitals: DashboardVitals,
    val modules: List<ModuleStatus>,
    val alerts: List<FarmAlert>,
    val energyTodayKwh: Double,
    val waterUsedTodayLiters: Int,
)
