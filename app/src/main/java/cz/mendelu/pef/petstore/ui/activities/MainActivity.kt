package cz.mendelu.pef.petstore.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cz.mendelu.pef.petstore.ui.navigation.MainNavHost
import cz.mendelu.pef.petstore.ui.theme.PetStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetStoreTheme {
                val navController = rememberNavController()

                MainNavHost(
                    modifier = Modifier.fillMaxSize(),
                    showLogin = { viewModel.showLogin() },
                    navController = navController,
                )
            }
        }
    }
}
