package uk.co.fractalmotion.mugshotsamplekmp.screens.water

/** Static sample data for previews - this sample has no real backend/data layer. */
object MockWaterData {
    val reservoirLevelPercent = 68
    val flowRateLpm = 4.6
    val waterTemperatureC = 19.8
    val waterPh = 6.3
    val pumpActive = true
    val nextCycleTime = "14:00"

    val irrigationLines = listOf(
        IrrigationLine(name = "Line 1 - Propagation", isActive = true, flowLpm = 1.2),
        IrrigationLine(name = "Line 2 - Vegetative", isActive = true, flowLpm = 1.8),
        IrrigationLine(name = "Line 3 - Flowering", isActive = false, flowLpm = 0.0),
        IrrigationLine(name = "Line 4 - Herbs", isActive = true, flowLpm = 0.9),
    )

    val default = WaterUiState(
        reservoirLevelPercent = reservoirLevelPercent,
        flowRateLpm = flowRateLpm,
        waterTemperatureC = waterTemperatureC,
        waterPh = waterPh,
        pumpActive = pumpActive,
        nextCycleTime = nextCycleTime,
        lines = irrigationLines,
    )
}
