package uk.co.fractalmotion.mugshotsamplekmp.screens.solar

/** Static sample data for previews - this sample has no real backend/data layer. */
object MockSolarData {
    val productionWatts = 3120
    val batteryPercent = 82
    val gridFeedWatts = 640
    val gridDrawWatts = 0
    val todayTotalKwh = 18.6
    val efficiencyPercent = 87

    val panels = listOf(
        SolarPanel(name = "Panel A", outputWatts = 820, efficiencyPercent = 94),
        SolarPanel(name = "Panel B", outputWatts = 790, efficiencyPercent = 91),
        SolarPanel(name = "Panel C", outputWatts = 540, efficiencyPercent = 62),
        SolarPanel(name = "Panel D", outputWatts = 970, efficiencyPercent = 97),
    )

    val default = SolarUiState(
        productionWatts = productionWatts,
        batteryPercent = batteryPercent,
        gridFeedWatts = gridFeedWatts,
        gridDrawWatts = gridDrawWatts,
        todayTotalKwh = todayTotalKwh,
        efficiencyPercent = efficiencyPercent,
        panels = panels,
    )
}
