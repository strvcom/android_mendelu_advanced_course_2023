package cz.mendelu.pef.petstore.ui.screens.petDetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.petstore.ui.elements.BaseScreen

@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel = hiltViewModel()
) {
    BaseScreen(
        topBarText = "Pet detail",
        drawFullScreenContent = false,
        showLoading = false,
        placeholderScreenContent = null
    ) {
        Text(text = "Random", color = Color.Red)
    }

}
