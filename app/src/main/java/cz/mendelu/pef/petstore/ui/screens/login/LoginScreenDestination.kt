package cz.mendelu.pef.petstore.ui.screens.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val RouteLogin = "routeLogin"

fun NavGraphBuilder.loginScreenDestination(
    navigateToListOfPets: () -> Unit
) {
    composable(
        route = RouteLogin,
    ) {
        LoginScreen(navigateToListOfPets = navigateToListOfPets)
    }
}
