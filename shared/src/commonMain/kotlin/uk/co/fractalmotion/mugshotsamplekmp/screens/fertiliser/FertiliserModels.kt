package uk.co.fractalmotion.mugshotsamplekmp.screens.fertiliser

data class NutrientTank(
    val name: String,
    val levelPercent: Int,
    val ec: Double,
    val ph: Double,
)

data class DoseEvent(
    val time: String,
    val tankName: String,
    val amountMl: Int,
)

data class FertiliserUiState(
    val tanks: List<NutrientTank>,
    val schedule: List<DoseEvent>,
)
