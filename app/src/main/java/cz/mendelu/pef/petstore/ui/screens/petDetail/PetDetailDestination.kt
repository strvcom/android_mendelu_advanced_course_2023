package cz.mendelu.pef.petstore.ui.screens.petDetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val RoutePetDetail = "routePetDetail"

data class PetDetailArgs(
    val petId: Long,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        petId = requireNotNull(savedStateHandle["petId"]),
    )
}

fun NavGraphBuilder.petDetailDestination() {
    composable(
        route = "$RoutePetDetail/{petId}",
        arguments = listOf(
            navArgument("petId") {
                type = NavType.LongType
            },
        ),
    ) {
        PetDetailScreen()
    }
}
