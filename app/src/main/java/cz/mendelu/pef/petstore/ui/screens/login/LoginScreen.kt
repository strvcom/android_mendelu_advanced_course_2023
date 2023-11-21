package cz.mendelu.pef.petstore.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.compose.todo.ui.elements.PlaceholderScreenContent
import cz.mendelu.pef.petstore.R
import cz.mendelu.pef.petstore.communication.user.LoginResponse
import cz.mendelu.pef.petstore.model.UiState
import cz.mendelu.pef.petstore.ui.elements.BaseScreen
import cz.mendelu.pef.petstore.ui.elements.RoundButton
import cz.mendelu.pef.petstore.ui.elements.TextInputField
import cz.mendelu.pef.petstore.ui.theme.basicMargin

const val TestTagLoginInputEmail = "TestTagLoginInputEmail"
const val TestTagLoginInputEmailError = "TestTagLoginInputEmailError"
const val TestTagLoginInputPassword = "TestTagLoginInputPassword"
const val TestTagLoginInputPasswordError = "TestTagLoginInputPasswordError"
const val TestTagLoginButton = "TestTagLoginButton"

@Composable
fun LoginScreen(
    navigateToListOfPets: () -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.loginUIState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState.data != null) {
            navigateToListOfPets()
        }
    }

    BaseScreen(
        topBarText = null,
        showLoading = uiState.loading,
        placeholderScreenContent = if (uiState.errors != null && uiState.errors!!.communicationError != null) {
            PlaceholderScreenContent(
                null,
                stringResource(id = uiState.errors!!.communicationError!!)
            )
        } else
            null
    ) {
        LoginScreenContent(
            actions = viewModel,
            uiState = uiState
        )
    }
}

@Composable
fun LoginScreenContent(
    uiState: UiState<LoginResponse, LoginErrors>,
    actions: LoginScreenActions
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(all = basicMargin()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        //  Input email
        TextInputField(
            modifier = Modifier
                .testTag(TestTagLoginInputEmail),
            value = email,
            hint = stringResource(R.string.email),
            onValueChange = { email = it },
            testTagErrorText = TestTagLoginInputEmailError,
            errorMessage = if (uiState.errors != null) uiState.errors!!.usernameError else null
        )

        //  Input password
        TextInputField(
            modifier = Modifier
                .testTag(TestTagLoginInputPassword),
            value = password,
            hint = stringResource(R.string.password),
            onValueChange = { password = it },
            errorMessage = if (uiState.errors != null) uiState.errors!!.passwordError else null,
            testTagErrorText = TestTagLoginInputPasswordError,
            keyboardType = KeyboardType.Password
        )

        RoundButton(
            modifier = Modifier
                .testTag(TestTagLoginButton),
            text = stringResource(R.string.sign_in),
            onClick = {
                actions.login(
                    email = email,
                    password = password
                )
            }
        )
    }
}