package uk.co.fractalmotion.mugshotsamplekmp.screens.solar

data class SolarPanel(
    val name: String,
    val outputWatts: Int,
    val efficiencyPercent: Int,
)

data class SolarUiState(
    val productionWatts: Int,
    val batteryPercent: Int,
    val gridFeedWatts: Int,
    val gridDrawWatts: Int,
    val todayTotalKwh: Double,
    val efficiencyPercent: Int,
    val panels: List<SolarPanel>,
)
