package cz.mendelu.pef.petstore.ui.screens.listofpets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.mendelu.pef.compose.todo.ui.elements.PlaceholderScreenContent
import cz.mendelu.pef.petstore.model.Pet
import cz.mendelu.pef.petstore.model.UiState
import cz.mendelu.pef.petstore.ui.elements.BaseScreen
import cz.mendelu.pef.petstore.ui.theme.basicTextColor

const val TestTagListOfPetsScreenContent = "TestTagListOfPetsScreenContent"

@Composable
fun ListOfPetsScreen(
    navigateToPetDetail: () -> Unit,
    viewModel: ListOfPetsViewModel = hiltViewModel()
) {
    val uiState: MutableState<UiState<List<Pet>, ListOfPetsErrors>> =
        rememberSaveable { mutableStateOf(UiState()) }

    viewModel.petsUIState.value.let {
        uiState.value = it
    }

    BaseScreen(
        topBarText = "List of pets",
        drawFullScreenContent = true,
        showLoading = uiState.value.loading,
        placeholderScreenContent = if (uiState.value.errors != null) {
            PlaceholderScreenContent(
                null,
                stringResource(id = uiState.value.errors!!.communicationError)
            )
        } else
            null
    ) {
        ListOfPetsScreenContent(
            paddingValues = it,
            uiState = uiState.value,
            onPetDetailClick = navigateToPetDetail,
        )
    }

}

@Composable
fun ListOfPetsScreenContent(
    onPetDetailClick: () -> Unit,
    paddingValues: PaddingValues,
    uiState: UiState<List<Pet>, ListOfPetsErrors>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .testTag(TestTagListOfPetsScreenContent),
    ) {
        if (uiState.data != null) {
            uiState.data!!.forEach { pet ->
                item {
                    pet.name?.let {
                        PetItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(vertical = 24.dp),
                            pet = pet,
                            onPetDetailClick = onPetDetailClick,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PetItem(
    pet: Pet,
    modifier: Modifier = Modifier,
    onPetDetailClick: () -> Unit,
) {
    val coverUrl = pet.photoUrls?.firstOrNull()

    Column(
        modifier = modifier
            .clickable { onPetDetailClick() }
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
