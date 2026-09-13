package uk.co.fractalmotion.mugshotsamplekmp.screens.water

data class IrrigationLine(
    val name: String,
    val isActive: Boolean,
    val flowLpm: Double,
)

data class WaterUiState(
    val reservoirLevelPercent: Int,
    val flowRateLpm: Double,
    val waterTemperatureC: Double,
    val waterPh: Double,
    val pumpActive: Boolean,
    val nextCycleTime: String,
    val lines: List<IrrigationLine>,
)
