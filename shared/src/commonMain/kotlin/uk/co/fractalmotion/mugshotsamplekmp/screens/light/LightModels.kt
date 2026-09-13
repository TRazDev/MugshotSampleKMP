package uk.co.fractalmotion.mugshotsamplekmp.screens.light

import org.jetbrains.compose.resources.StringResource
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

data class LightZone(
    val name: String,
    val isOn: Boolean,
    val intensityPercent: Int,
    val onTime: String,
    val offTime: String,
    val dailyHours: Double,
    /** Non-null when the zone has a fault (e.g. ballast failure) - shown as a critical status pill and inline warning. */
    val faultMessage: String? = null,
)

data class LightUiState(
    val zones: List<LightZone>,
    val headerStatusTextRes: StringResource,
    val headerStatusTone: StatusTone,
)
