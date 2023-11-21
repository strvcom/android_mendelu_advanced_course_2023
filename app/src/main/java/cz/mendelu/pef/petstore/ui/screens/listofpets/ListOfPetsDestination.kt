package cz.mendelu.pef.petstore.ui.screens.listofpets

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val RouteListOfPets = "routeListOfPets"

fun NavGraphBuilder.listOfPetsScreenDestination(
    navigateToPetDetail: () -> Unit,
) {
    composable(
        route = RouteListOfPets,
    ) {
        ListOfPetsScreen(
            navigateToPetDetail = navigateToPetDetail,
        )
    }
}
