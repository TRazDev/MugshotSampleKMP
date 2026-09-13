package uk.co.fractalmotion.mugshotsamplekmp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mugshotsamplekmp.shared.generated.resources.Res
import mugshotsamplekmp.shared.generated.resources.nav_dashboard
import mugshotsamplekmp.shared.generated.resources.nav_fertiliser
import mugshotsamplekmp.shared.generated.resources.nav_light
import mugshotsamplekmp.shared.generated.resources.nav_solar
import mugshotsamplekmp.shared.generated.resources.nav_water
import org.jetbrains.compose.resources.stringResource
import uk.co.fractalmotion.mugshotsamplekmp.components.NavItem
import uk.co.fractalmotion.mugshotsamplekmp.components.PixelBottomNav
import uk.co.fractalmotion.mugshotsamplekmp.screens.dashboard.DashboardScreen
import uk.co.fractalmotion.mugshotsamplekmp.screens.dashboard.MockDashboardData
import uk.co.fractalmotion.mugshotsamplekmp.screens.fertiliser.FertiliserScreen
import uk.co.fractalmotion.mugshotsamplekmp.screens.fertiliser.MockFertiliserData
import uk.co.fractalmotion.mugshotsamplekmp.screens.light.LightScreen
import uk.co.fractalmotion.mugshotsamplekmp.screens.light.MockLightData
import uk.co.fractalmotion.mugshotsamplekmp.screens.solar.MockSolarData
import uk.co.fractalmotion.mugshotsamplekmp.screens.solar.SolarScreen
import uk.co.fractalmotion.mugshotsamplekmp.screens.water.MockWaterData
import uk.co.fractalmotion.mugshotsamplekmp.screens.water.WaterScreen
import uk.co.fractalmotion.mugshotsamplekmp.theme.ModuleAccent
import uk.co.fractalmotion.mugshotsamplekmp.theme.MugshotAppTheme

/**
 * Just enough local-state tab switching to see all 5 demo screens on a real device -
 * no navigation library or back-stack, since this sample has no real app behind it.
 */
@Composable
@Preview
fun App() {
    MugshotAppTheme {
        var selectedTab by remember { mutableStateOf(0) }
        val navItems = listOf(
            NavItem("D", stringResource(Res.string.nav_dashboard), ModuleAccent.Dashboard.tone),
            NavItem("L", stringResource(Res.string.nav_light), ModuleAccent.Light.tone),
            NavItem("F", stringResource(Res.string.nav_fertiliser), ModuleAccent.Fertiliser.tone),
            NavItem("W", stringResource(Res.string.nav_water), ModuleAccent.Water.tone),
            NavItem("S", stringResource(Res.string.nav_solar), ModuleAccent.Solar.tone),
        )

        Column(Modifier.fillMaxSize()) {
            Column(Modifier.weight(1f)) {
                when (selectedTab) {
                    0 -> DashboardScreen(state = MockDashboardData.default)
                    1 -> LightScreen(state = MockLightData.default)
                    2 -> FertiliserScreen(state = MockFertiliserData.default)
                    3 -> WaterScreen(state = MockWaterData.default)
                    else -> SolarScreen(state = MockSolarData.default)
                }
            }
            PixelBottomNav(
                items = navItems,
                selectedIndex = selectedTab,
                onSelect = { selectedTab = it },
            )
        }
    }
}
