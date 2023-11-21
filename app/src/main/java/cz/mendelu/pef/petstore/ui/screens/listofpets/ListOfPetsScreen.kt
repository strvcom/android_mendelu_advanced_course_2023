package cz.mendelu.pef.petstore.ui.screens.listofpets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.pef.compose.todo.ui.elements.PlaceholderScreenContent
import cz.mendelu.pef.petstore.model.Pet
import cz.mendelu.pef.petstore.model.UiState
import cz.mendelu.pef.petstore.ui.elements.BaseScreen
import cz.mendelu.pef.petstore.ui.theme.basicTextColor

const val TestTagListOfPets = "listOfPets"

@Destination
@Composable
fun ListOfPetsScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<ListOfPetsViewModel>()

    val uiState: MutableState<UiState<List<Pet>, ListOfPetsErrors>> =
        rememberSaveable { mutableStateOf(UiState()) }

    viewModel.petsUIState.value.let {
        uiState.value = it
    }

    BaseScreen(
        modifier = Modifier
            .testTag(TestTagListOfPets),
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
        ListOfPetsScreenContent(paddingValues = it, uiState = uiState.value)
    }

}

@Composable
fun ListOfPetsScreenContent(
    paddingValues: PaddingValues,
    uiState: UiState<List<Pet>, ListOfPetsErrors>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
    ) {
        if (uiState.data != null) {
            uiState.data!!.forEach {
                item {
                    it.name?.let {
                        Text(text = it, color = basicTextColor())
                    }
                }
            }
        }
    }
}