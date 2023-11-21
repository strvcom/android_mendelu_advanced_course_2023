package cz.mendelu.pef.petstore.ui.screens.petDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.mendelu.pef.petstore.model.Pet
import cz.mendelu.pef.petstore.ui.elements.BaseScreen
import cz.mendelu.pef.petstore.ui.theme.basicTextColor

@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.petDetailUiState.collectAsState()

    BaseScreen(
        topBarText = "Pet detail",
        drawFullScreenContent = false,
        showLoading = false,
        placeholderScreenContent = null
    ) {
        val pet = uiState.data
        if (pet != null) {
            PetDetailScreenContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                pet = pet
            )
        } else {
            Text(
                text = "Pet not found!",
                color = basicTextColor()
            )
        }
    }
}

@Composable
private fun PetDetailScreenContent(
    pet: Pet,
    modifier: Modifier = Modifier,
) {
    val coverUrl = pet.photoUrls?.firstOrNull()

    Column(
        modifier = modifier
    ) {
        coverUrl?.let {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds,
                model = coverUrl,
                contentDescription = "Loaded image"
            )
            Text(
                text = pet.name.orEmpty(),
                color = basicTextColor()
            )
        }
    }
}

