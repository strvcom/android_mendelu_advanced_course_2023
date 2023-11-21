package cz.mendelu.pef.petstore.ui.activities

import cz.mendelu.pef.petstore.architecture.BaseViewModel
import cz.mendelu.pef.petstore.datastore.IDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val dataStoreRepository: IDataStoreRepository
) : BaseViewModel() {

    fun showLogin(): Boolean {
        return runBlocking { dataStoreRepository.getLoginSuccessful().not() }
    }
}
