package cz.mendelu.pef.petstore.ui.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.petstore.R
import cz.mendelu.pef.petstore.architecture.BaseViewModel
import cz.mendelu.pef.petstore.architecture.CommunicationResult
import cz.mendelu.pef.petstore.communication.user.LoginResponse
import cz.mendelu.pef.petstore.communication.user.UserRemoteRepositoryImpl
import cz.mendelu.pef.petstore.datastore.IDataStoreRepository
import cz.mendelu.pef.petstore.extension.isValidEmail
import cz.mendelu.pef.petstore.extension.isValidPassword
import cz.mendelu.pef.petstore.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel
@Inject constructor(
    private val dataStoreRepository: IDataStoreRepository,
    private val userRemoteRepository: UserRemoteRepositoryImpl
) : BaseViewModel(), LoginScreenActions {

    val loginUIState: MutableState<UiState<LoginResponse, LoginErrors>> =
        mutableStateOf(UiState(loading = false))

    override fun login(email: String, password: String) {
        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isValidPassword()

        if (isEmailValid.not() || isPasswordValid.not()) {
            loginUIState.value = UiState(
                loading = false,
                data = null,
                errors = LoginErrors(
                    usernameError = if (isEmailValid) null else "Email is not valid.",
                    passwordError = if (isPasswordValid) null else "Password is not valid.",
                )
            )
        } else {
            launch {
                val result = withContext(Dispatchers.IO) {
                    userRemoteRepository.login(email, password)
                }
                when (result) {
                    is CommunicationResult.Success -> {
                        dataStoreRepository.setLoginSuccessful()
                        loginUIState.value =
                            UiState(loading = false, data = result.data, errors = null)
                    }

                    is CommunicationResult.Error -> {
                        loginUIState.value = UiState(
                            loading = false, data = null,
                            errors = LoginErrors(communicationError = R.string.login_failed)
                        )
                    }

                    is CommunicationResult.Exception -> {
                        loginUIState.value = UiState(
                            loading = false, data = null,
                            errors = LoginErrors(communicationError = R.string.unknown_error)
                        )
                    }

                    is CommunicationResult.ConnectionError -> {
                        loginUIState.value = UiState(
                            loading = false, data = null,
                            errors = LoginErrors(communicationError = R.string.no_internet_connection)
                        )
                    }
                }
            }
        }
    }
}