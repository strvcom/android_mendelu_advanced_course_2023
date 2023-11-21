package cz.mendelu.pef.petstore

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import cz.mendelu.pef.petstore.ui.activities.MainActivity
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputEmail
import cz.mendelu.pef.petstore.ui.screens.login.TestTagLoginInputPassword
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

typealias MainActivityComposeRule = AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>

@ExperimentalCoroutinesApi
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UITests {

    private val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var navController: NavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    //val composeRule = createAndroidComposeRule<MainActivity>()
    val composeRule: MainActivityComposeRule = createAndroidComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    // Priority to do: High
    @Test
    fun test_report_email_valid() {
        //launchReportScreen()
        with(composeRule) {
            onNodeWithTag(TestTagLoginInputEmail).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputPassword).assertIsDisplayed()
            onNodeWithTag(TestTagLoginInputPassword).assertIsNotDisplayed()
            onNodeWithTag(TestTagLoginInputEmail).performTextInput("test@email.com")

            // Explain: Why not this?
            //composeRule.onNodeWithTag(TEST_TAG_REPORT_EMAIL_ERROR, useUnmergedTree = true).assertIsNotDisplayed()
            onNode(
                //hasText(targetContext.resources.getString(R.string.report_enter_email_invalid)),
                hasText("random"),
            ).assertDoesNotExist()
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
}