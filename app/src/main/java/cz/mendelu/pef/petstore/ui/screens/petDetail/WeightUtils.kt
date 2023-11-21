package cz.mendelu.pef.petstore.ui.screens.petDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

enum class WeightUnitsEnum(
    private val unit: String,
    val displayedOption: String,
) {
    POUNDS(
        displayedOption = "Pounds [lbs]",
        unit = "lbs"
    ),
    KILOGRAMS(
        displayedOption = "Kilograms [kgs]",
        unit = "kgs"
    );

    companion object {
        private const val ONE_KG_TO_LBS = 2.20462

        fun convertKgsToLbs(kgs: Double): Double {
            return kgs * ONE_KG_TO_LBS
        }

        fun convertLbsToKgs(lbs: Double): Double {
            return lbs.div(ONE_KG_TO_LBS)
        }
    }

    /**
     *  Convert base celsius temperature to selected unit system and format to string value with corresponding symbol.
     *  @param valueCelsius value in base unit - Celsius
     *
     *  @return "12.0 kgs" for KGS
     *  @return "5.674 lbs" for LBS
     * */
    @Composable
    @ReadOnlyComposable
    fun formatWeight(weightKgs: Double): String {
        val weightInSelectedUnit = when (this) {
            POUNDS -> convertKgsToLbs(weightKgs)
            KILOGRAMS -> weightKgs
        }
        return "${String.format("%.3f", weightInSelectedUnit)} ${this.unit}"
    }
}