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
import cz.mendelu.pef.petstore.mock.ServerMock
import cz.mendelu.pef.petstore.ui.activities.MainActivity
import cz.mendelu.pef.petstore.ui.navigation.MainNavHost
import cz.mendelu.pef.petstore.ui.screens.listofpets.TestTagListOfPetsScreenLazyList
import cz.mendelu.pef.petstore.ui.screens.petDetail.RoutePetDetail
import cz.mendelu.pef.petstore.ui.screens.petDetail.TestTagPetDetailName
import cz.mendelu.pef.petstore.ui.screens.petDetail.TestTagPetDetailRadioButtonKilograms
import cz.mendelu.pef.petstore.ui.screens.petDetail.TestTagPetDetailRadioButtonPounds
import cz.mendelu.pef.petstore.ui.screens.petDetail.TestTagPetDetailWeightText
import cz.mendelu.pef.petstore.ui.screens.petDetail.WeightUnitsEnum
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert
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

@ExperimentalCoroutinesApi
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UITestsPetDetail {

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
    fun test_navigate_to_correct_pet_detail_screen() {
        launchListOfPetsScreenWithNavigation()
        with(composeRule) {
            val targetPet = ServerMock.all.first { pet -> pet.name == "Afrodita" }
            assert(targetPet.id != null)
            assert(targetPet.name != null)
            onNodeWithTag(TestTagListOfPetsScreenLazyList).assertIsDisplayed()

            onNode(hasText(targetPet.name!!)).assertIsDisplayed()
            onNode(hasText(targetPet.name!!)).performClick()
            waitForIdle()

            Thread.sleep(500)   // Just for better visibility
            val route = navController.currentBackStackEntry?.destination?.route
            Assert.assertTrue(route?.contains(RoutePetDetail) ?: false)

            onNodeWithTag(TestTagPetDetailName).assertTextEquals(targetPet.name!!)
        }
    }

    @Test
    fun test_scroll_and_navigate_to_correct_pet_detail_screen() {
        launchListOfPetsScreenWithNavigation()
        with(composeRule) {
            val targetPetName = "Carlitto"
            onNodeWithTag(TestTagListOfPetsScreenLazyList).assertIsDisplayed()
            onNodeWithTag(TestTagListOfPetsScreenLazyList).performScrollToNode(hasText(targetPetName))

            waitForIdle()
            Thread.sleep(500) // Just to better see

            onNode(hasText(targetPetName)).assertIsDisplayed()
            onNode(hasText(targetPetName)).performClick()

            waitForIdle()
            Thread.sleep(1000)   // Just for better visibility

            val route = navController.currentBackStackEntry?.destination?.route
            Assert.assertTrue(route?.contains(RoutePetDetail) ?: false)

            onNodeWithTag(TestTagPetDetailName).assertTextEquals(targetPetName)
        }
    }

    @Test
    fun test_scroll_and_navigate_to_correct_pet_detail_screen_switch() {
        launchListOfPetsScreenWithNavigation()
        with(composeRule) {
            val targetPetName = "Carlitto"
            onNodeWithTag(TestTagListOfPetsScreenLazyList).assertIsDisplayed()
            onNodeWithTag(TestTagListOfPetsScreenLazyList).performScrollToNode(hasText(targetPetName))

            waitForIdle()
            Thread.sleep(500) // Just to better see

            onNode(hasText(targetPetName)).assertIsDisplayed()
            onNode(hasText(targetPetName)).performClick()

            waitForIdle()
            Thread.sleep(1000)   // Just for better visibility

            val route = navController.currentBackStackEntry?.destination?.route
            Assert.assertTrue(route?.contains(RoutePetDetail) ?: false)

            onNodeWithTag(TestTagPetDetailName).assertTextEquals(targetPetName)

            onNodeWithTag(TestTagPetDetailWeightText).assertIsDisplayed()
            onNodeWithTag(TestTagPetDetailRadioButtonKilograms).assertIsDisplayed()
            onNodeWithTag(TestTagPetDetailRadioButtonPounds).assertIsDisplayed()

            onNodeWithTag(TestTagPetDetailWeightText).assertTextContains(
                value = WeightUnitsEnum.KILOGRAMS.unit,
                substring = true,
                ignoreCase = true
            )
            onNodeWithTag(TestTagPetDetailRadioButtonPounds).performClick()

            waitForIdle()
            Thread.sleep(1000)   // Just for better visibility

            onNodeWithTag(TestTagPetDetailWeightText).assertTextContains(
                value = WeightUnitsEnum.POUNDS.unit,
                substring = true,
                ignoreCase = true
            )
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