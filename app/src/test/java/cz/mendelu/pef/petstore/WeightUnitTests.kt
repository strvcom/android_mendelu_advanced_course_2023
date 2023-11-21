package cz.mendelu.pef.petstore

import cz.mendelu.pef.petstore.ui.screens.petDetail.WeightUnitsEnum
import org.junit.Assert
import org.junit.Test

class WeightUnitTests {

    // Priority to do: High
    @Test
    fun kilogramToLbs_isCorrect1() {
        Assert.assertEquals(
            /* expected = */ 22.04,
            /* actual = */ WeightUnitsEnum.convertKgsToLbs(10.0),
            /* delta = */ 0.1
        )
    }

    // Priority to do: Low
    @Test
    fun lbsToKilogram_isCorrect2() {
        Assert.assertEquals(
            /* expected = */ 10.0,
            /* actual = */ WeightUnitsEnum.convertLbsToKgs(22.04),
            /* delta = */ 0.1
        )
    }
}