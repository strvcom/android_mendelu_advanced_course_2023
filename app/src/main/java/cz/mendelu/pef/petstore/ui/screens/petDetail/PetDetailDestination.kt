package cz.mendelu.pef.petstore.ui.screens.petDetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val RoutePetDetail = "routePetDetail"

fun NavGraphBuilder.petDetailDestination() {
    composable(
        route = RoutePetDetail,
    ) {
        PetDetailScreen()
    }
}
