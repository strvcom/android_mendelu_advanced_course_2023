package cz.mendelu.pef.petstore.ui.screens.petDetail

import androidx.lifecycle.SavedStateHandle
import cz.mendelu.pef.petstore.architecture.BaseViewModel
import cz.mendelu.pef.petstore.mock.ServerMock
import cz.mendelu.pef.petstore.model.Pet
import cz.mendelu.pef.petstore.model.UiState
import cz.mendelu.pef.petstore.ui.screens.login.LoginErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val args = PetDetailArgs(savedStateHandle)

    val petDetailUiState: MutableStateFlow<UiState<Pet, LoginErrors>> =
        MutableStateFlow(UiState(loading = true))

    init {
        Timber.d("PetDetailViewModel init, args = $args")
        petDetailUiState.update {
            UiState(
                loading = false,
                errors = null,
                data = ServerMock.all.firstOrNull {
                    it.id == args.petId
                },
            )
        }
    }
}