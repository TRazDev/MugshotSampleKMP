package uk.co.fractalmotion.mugshotsamplekmp.screens.light

import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.common_auto
import mugshotsamplekmp.shared.generated.resources.common_critical
import uk.co.fractalmotion.mugshotsamplekmp.theme.StatusTone

/** Static sample data for previews - this sample has no real backend/data layer. */
object MockLightData {
    private val zoneA = LightZone(name = "Zone A - Propagation", isOn = true, intensityPercent = 85, onTime = "06:00", offTime = "22:00", dailyHours = 16.0)
    private val zoneB = LightZone(name = "Zone B - Vegetative", isOn = true, intensityPercent = 100, onTime = "05:30", offTime = "23:30", dailyHours = 18.0)
    private val zoneC = LightZone(name = "Zone C - Flowering", isOn = false, intensityPercent = 60, onTime = "07:00", offTime = "19:00", dailyHours = 12.0)
    private val zoneD = LightZone(name = "Zone D - Herbs", isOn = true, intensityPercent = 70, onTime = "06:00", offTime = "20:00", dailyHours = 14.0)

    val nominal = LightUiState(
        zones = listOf(zoneA, zoneB, zoneC, zoneD),
        headerStatusTextRes = Res.string.common_auto,
        headerStatusTone = StatusTone.Nominal,
    )

    val critical = LightUiState(
        zones = listOf(
            zoneA,
            zoneB.copy(
                isOn = false,
                intensityPercent = 0,
                faultMessage = "Ballast fault - zone offline",
            ),
            zoneC,
            zoneD,
        ),
        headerStatusTextRes = Res.string.common_critical,
        headerStatusTone = StatusTone.Critical,
    )

    val default = nominal
}
