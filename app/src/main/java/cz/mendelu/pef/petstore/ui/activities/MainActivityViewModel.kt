package cz.mendelu.pef.petstore.ui.activities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.petstore.architecture.BaseViewModel
import cz.mendelu.pef.petstore.communication.user.LoginResponse
import cz.mendelu.pef.petstore.communication.user.UserRemoteRepositoryImpl
import cz.mendelu.pef.petstore.datastore.IDataStoreRepository
import cz.mendelu.pef.petstore.model.UiState
import cz.mendelu.pef.petstore.ui.screens.login.LoginErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val dataStoreRepository: IDataStoreRepository) : BaseViewModel() {

    val showLogin: MutableState<Boolean> = mutableStateOf(true)

    init {
        launch {
            showLogin.value = !dataStoreRepository.getLoginSuccessful()
        }
    }

}
