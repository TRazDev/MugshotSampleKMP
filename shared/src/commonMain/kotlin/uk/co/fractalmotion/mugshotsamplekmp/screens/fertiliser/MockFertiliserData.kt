package uk.co.fractalmotion.mugshotsamplekmp.screens.fertiliser

/** Static sample data for previews - this sample has no real backend/data layer. */
object MockFertiliserData {
    val tanks = listOf(
        NutrientTank(name = "Tank A - Grow", levelPercent = 78, ec = 1.8, ph = 6.1),
        NutrientTank(name = "Tank B - Bloom", levelPercent = 34, ec = 2.1, ph = 5.9),
        NutrientTank(name = "Tank C - CalMag", levelPercent = 91, ec = 1.2, ph = 6.4),
    )

    val doseSchedule = listOf(
        DoseEvent(time = "08:00", tankName = "Tank A - Grow", amountMl = 120),
        DoseEvent(time = "14:00", tankName = "Tank B - Bloom", amountMl = 90),
        DoseEvent(time = "20:00", tankName = "Tank C - CalMag", amountMl = 60),
    )

    val default = FertiliserUiState(tanks = tanks, schedule = doseSchedule)
}
