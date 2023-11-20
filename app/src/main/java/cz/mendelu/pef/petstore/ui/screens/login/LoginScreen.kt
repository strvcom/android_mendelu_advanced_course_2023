package cz.mendelu.pef.petstore.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.mendelu.pef.compose.todo.ui.elements.PlaceholderScreenContent
import cz.mendelu.pef.petstore.R
import cz.mendelu.pef.petstore.communication.user.LoginResponse
import cz.mendelu.pef.petstore.model.Pet
import cz.mendelu.pef.petstore.model.UiState
import cz.mendelu.pef.petstore.ui.elements.BaseScreen
import cz.mendelu.pef.petstore.ui.elements.RoundButton
import cz.mendelu.pef.petstore.ui.elements.TextInputField
import cz.mendelu.pef.petstore.ui.screens.destinations.ListOfPetsScreenDestination
import cz.mendelu.pef.petstore.ui.screens.listofpets.ListOfPetsScreen
import cz.mendelu.pef.petstore.ui.screens.listofpets.ListOfPetsScreenContent
import cz.mendelu.pef.petstore.ui.theme.basicMargin


@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<LoginScreenViewModel>()

    val uiState: MutableState<UiState<LoginResponse, LoginErrors>> =
        rememberSaveable { mutableStateOf(UiState()) }

    viewModel.loginUIState.value.let {
        uiState.value = it
        if(it.data != null){
            LaunchedEffect(it){
                navigator.navigate(ListOfPetsScreenDestination())
            }
        }
    }

    BaseScreen(
        topBarText = null,
        showLoading = uiState.value.loading,
        placeholderScreenContent = if (uiState.value.errors != null && uiState.value.errors!!.communicationError != null){
            PlaceholderScreenContent(null, stringResource(id = uiState.value.errors!!.communicationError!!))
        } else
            null
    ) {
        LoginScreenContent(
            paddingValues = it,
            actions = viewModel,
            uiState = uiState.value)
    }
}

@Composable
fun LoginScreenContent(
    paddingValues: PaddingValues,
    uiState: UiState<LoginResponse, LoginErrors>,
    actions: LoginScreenActions
    ){

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(all = basicMargin()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        TextInputField(value = username, hint = stringResource(R.string.email), onValueChange = {
            username = it
        }, errorMessage = if (uiState.errors != null) uiState.errors!!.usernameError else null)

        TextInputField(value = password, hint = stringResource(R.string.password), onValueChange = {
            password = it
        }, errorMessage = if (uiState.errors != null) uiState.errors!!.passwordError else null,
            keyboardType = KeyboardType.Password)

        RoundButton(
            text = stringResource(R.string.sign_in), onClick = {
                actions.login(username, password)
            })

    }
}