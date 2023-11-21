package cz.mendelu.pef.petstore.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import cz.mendelu.pef.petstore.ui.screens.NavGraphs
import cz.mendelu.pef.petstore.ui.screens.destinations.ListOfPetsScreenDestination
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
