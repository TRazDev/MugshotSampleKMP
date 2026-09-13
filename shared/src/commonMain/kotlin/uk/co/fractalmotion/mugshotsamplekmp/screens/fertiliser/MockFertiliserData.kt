package uk.co.fractalmotion.mugshotsamplekmp.screens.fertiliser

/** Static sample data for previews - this sample has no real backend/data layer. */
object MockFertiliserData {
    val tanks = listOf(
        NutrientTank("Tank A - Grow", levelPercent = 78, ec = 1.8, ph = 6.1),
        NutrientTank("Tank B - Bloom", levelPercent = 34, ec = 2.1, ph = 5.9),
        NutrientTank("Tank C - CalMag", levelPercent = 91, ec = 1.2, ph = 6.4),
    )

    val doseSchedule = listOf(
        DoseEvent("08:00", "Tank A - Grow", 120),
        DoseEvent("14:00", "Tank B - Bloom", 90),
        DoseEvent("20:00", "Tank C - CalMag", 60),
    )

    val default = FertiliserUiState(tanks = tanks, schedule = doseSchedule)
}
