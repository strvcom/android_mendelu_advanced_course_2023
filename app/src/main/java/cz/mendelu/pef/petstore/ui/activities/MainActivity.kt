package cz.mendelu.pef.petstore.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import cz.mendelu.pef.petstore.communication.user.LoginResponse
import cz.mendelu.pef.petstore.model.UiState
import cz.mendelu.pef.petstore.ui.screens.NavGraphs
import cz.mendelu.pef.petstore.ui.screens.destinations.ListOfPetsScreenDestination
import cz.mendelu.pef.petstore.ui.screens.login.LoginErrors
import cz.mendelu.pef.petstore.ui.screens.login.LoginScreenViewModel
import cz.mendelu.pef.petstore.ui.theme.PetStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetStoreTheme {
                val viewModel = hiltViewModel<MainActivityViewModel>()

                viewModel.showLogin.value.let {
                    if (it) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            startRoute = ListOfPetsScreenDestination
                        )
                    } else {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root
                        )
                    }
                }
            }
        }
    }
}
