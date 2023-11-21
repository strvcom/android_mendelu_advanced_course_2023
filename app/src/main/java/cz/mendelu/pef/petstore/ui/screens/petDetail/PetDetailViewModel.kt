package cz.mendelu.pef.petstore.ui.screens.petDetail

import cz.mendelu.pef.petstore.architecture.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
) : BaseViewModel() {

    init {
        Timber.d("PetDetailViewModel init")
    }
}