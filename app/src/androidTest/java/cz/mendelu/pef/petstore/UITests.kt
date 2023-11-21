package cz.mendelu.pef.petstore

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import cz.mendelu.pef.petstore.ui.activities.MainActivity
import cz.mendelu.pef.petstore.ui.navigation.MainNavHost
import cz.mendelu.pef.petstore.ui.screens.listofpets.RouteListOfPets
import cz.mendelu.pef.petstore.ui.screens.listofpets.TestTagListOfPetsScreenContent
import cz.mendelu.pef.petstore.ui.screens.login.RouteLogin
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginButton
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputEmail
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputEmailError
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputPassword
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputPasswordError
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters
import timber.log.Timber


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExperimentalCoroutinesApi
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UITests {

    private val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var navController: NavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_input_valid_credentials_and_navigate_next() {
        launchLoginScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TestTagLoginInputEmail).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputPassword).assertIsDisplayed()

            onNodeWithTag(TestTagLoginInputEmail).performTextInput("test@email.com")
            onNodeWithTag(TestTagLoginInputPassword).performTextInput("heslo123")

            onNodeWithTag(TestTagLoginButton).performClick()
            waitForIdle()

            val route = navController.currentBackStackEntry?.destination?.route
            Thread.sleep(500)
            assertTrue(route == RouteListOfPets)
        }
    }

    @Test
    fun test_input_invalid_credentials_and_stay_on_login() {
        launchLoginScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TestTagLoginInputEmail).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputPassword).assertIsDisplayed()

            onNodeWithTag(TestTagLoginInputEmail).performTextInput("test@email.com")
            onNodeWithTag(TestTagLoginInputPassword).performTextInput("h")

            onNodeWithTag(TestTagLoginButton).performClick()
            waitForIdle()

            val route = navController.currentBackStackEntry?.destination?.route
            Thread.sleep(500)
            assertTrue(route == RouteLogin)
        }
    }

    @Test
    fun test_input_invalid_email_and_display_error() {
        launchLoginScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TestTagLoginInputEmail).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputPassword).assertIsDisplayed()

            onNodeWithTag(TestTagLoginInputEmail).performTextInput("invalidmail")
            onNodeWithTag(TestTagLoginInputPassword).performTextInput("heslo123")

            onNodeWithTag(TestTagLoginButton).performClick()
            onNodeWithTag(TestTagLoginInputEmailError).assertIsDisplayed()
        }
    }

    //  TODO: show valid email and email error is not displayed
    @Test
    fun test_input_invalid_password_and_display_error() {
        launchLoginScreenWithNavigation()
        with(composeRule) {
            onNodeWithTag(TestTagLoginInputEmail).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputPassword).assertIsDisplayed()

            onNodeWithTag(TestTagLoginInputEmail).performTextInput("valid@email.com")
            onNodeWithTag(TestTagLoginInputPassword).performTextInput("a")

            onNodeWithTag(TestTagLoginButton).performClick()
            onNodeWithTag(TestTagLoginInputPasswordError).assertIsDisplayed()
        }
    }

    @Test
    fun test_launch_list_of_pets() {
        launchListOfPetsScreenWithNavigation()
        with(composeRule) {
            println("XXX start")
            Timber.d("XXX start")
            //val screen = hasTestTag(TestTagListOfPetsScreenContent)
            //waitUntilAtLeastOneExists(screen, 1_000)
            onNodeWithTag(TestTagListOfPetsScreenContent).assertIsDisplayed()
            Thread.sleep(1000)
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun launchLoginScreenWithNavigation() {
        composeRule.activity.setContent {
            MaterialTheme {
                navController = rememberNavController()
                MainNavHost(
                    navController = navController,
                    showLogin = { true },
                )
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun launchListOfPetsScreenWithNavigation() {
        composeRule.activity.setContent {
            MaterialTheme {
                navController = rememberNavController()
                MainNavHost(
                    navController = navController,
                    showLogin = { false },
                )
            }
        }
    }
}