package cz.mendelu.pef.petstore.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import cz.mendelu.pef.petstore.ui.screens.listofpets.RouteListOfPets
import cz.mendelu.pef.petstore.ui.screens.listofpets.listOfPetsScreenDestination
import cz.mendelu.pef.petstore.ui.screens.login.RouteLogin
import cz.mendelu.pef.petstore.ui.screens.login.loginScreenDestination

@ExperimentalAnimationApi
@Composable
internal fun MainNavHost(
    showLogin: () -> Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val startDestination: String by remember(Unit) {
        mutableStateOf(
            if (showLogin()) RouteLogin else RouteListOfPets,
        )
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        loginScreenDestination(
            navigateToListOfPets = {
                navController.navigate(
                    route = RouteListOfPets
                )
            }
        )

        listOfPetsScreenDestination()
    }
}
