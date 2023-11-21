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
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginButton
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputEmail
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputPassword
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
    fun test_report_email_valid_d() {
        launchReportScreenWithNavigation()
        with(composeRule) {
            println("XXX start")
            Timber.d("XXX start")
            onNodeWithTag(TestTagLoginInputEmail).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputPassword).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputEmail).performTextInput("test@email.com")
            onNodeWithTag(TestTagLoginInputPassword).performTextInput("heslo123")
            onNodeWithTag(TestTagLoginButton).performClick()
            println("XXX before waitForIdle")
            Timber.d("XXX before waitForIdle")
            waitForIdle()
            println("XXX after waitForIdle")
            Timber.d("XXX after waitForIdle")

            val route = navController.currentBackStackEntry?.destination?.route
            Thread.sleep(500)
            println("XXX route = $route")
            Timber.d("XXX route = $route")
            assertTrue(route == RouteListOfPets)

            // Explain: Why not this?
            //composeRule.onNodeWithTag(TEST_TAG_REPORT_EMAIL_ERROR, useUnmergedTree = true).assertIsNotDisplayed()
            /*onNode(
                //hasText(targetContext.resources.getString(R.string.report_enter_email_invalid)),
                hasText("List of pets"),
            ).assertDoesNotExist()*/
        }
    }

    // Priority to do: High
    /*@Test
    fun test_report_email_valid() {
        launchReportScreen()
        with(composeRule) {
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).assertIsDisplayed()
            onNodeWithTag(TEST_TAG_REPORT_EMAIL_INPUT).performTextInput("test@email.com")

            // Explain: Why not this?
            //composeRule.onNodeWithTag(TEST_TAG_REPORT_EMAIL_ERROR, useUnmergedTree = true).assertIsNotDisplayed()
            onNode(
                hasText(targetContext.resources.getString(R.string.report_enter_email_invalid)),
            ).assertDoesNotExist()
        }
    }

    private fun launchReportScreen() {
        composeRule.setContent {
            val reportViewModel = initReportViewModel() // Explain: inaccessible by design
            MaterialTheme {
                ReportScreen(
                    viewModel = reportViewModel, // Explain: this line can be deleted
                    navigateToSuccessScreen = {},
                    navigateToFailScreen = {},
                )
            }
        }
    }

    private fun initReportViewModel(): ReportViewModel {
        return composeRule.activity.viewModels<ReportViewModel>().value
    }*/


    @OptIn(ExperimentalAnimationApi::class)
    private fun launchReportScreenWithNavigation() {
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
}