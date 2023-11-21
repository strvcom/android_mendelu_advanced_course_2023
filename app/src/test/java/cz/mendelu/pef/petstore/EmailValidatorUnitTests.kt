package cz.mendelu.pef.petstore

import cz.mendelu.pef.petstore.extension.isValidEmail
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class EmailValidatorUnitTests {

    // Priority to do: High
    @Test
    fun email_isValid() {
        val emailInput = "email@test.com"
        assertEquals(
            /* expected = */ true,
            /* actual = */ emailInput.isValidEmail(),
        )
    }

    // Priority to do: High
    @Test
    fun email_isNotValid_1() {
        val emailInput = "emailtest.com"
        assertEquals(
            /* expected = */ false,
            /* actual = */ emailInput.isValidEmail(),
        )
    }

    // Priority to do: Low
    @Test
    fun email_isNotValid_2() {
        val emailInput = "email@testcom"
        assertEquals(
            /* expected = */ true,
            /* actual = */ emailInput.isValidEmail().not(),
        )
    }

    // Priority to do: Low
    @Test
    fun email_isNotValid_3() {
        val emailInput = "emailtestcom"
        assertNotEquals(
            /* unexpected = */ true,
            /* actual = */ emailInput.isValidEmail(),
        )
    }

    // Priority to do: Low
    @Test
    fun email_isNotValid_4() {
        val emailInput = "emailtestcom"
        assertNotEquals(
            /* unexpected = */ false,
            /* actual = */ emailInput.isValidEmail().not(),
        )
    }

    // Priority to do: Low
    @Test
    fun email_isNotValid_5() {
        val emailInput = "email@@test.com"
        assertNotEquals(
            /* unexpected = */ true,
            /* actual = */ emailInput.isValidEmail(),
        )
    }

    // Priority to do: Low
    @Test
    fun email_isNotValid_5_same_but_different() {
        val emailInput = "email@@test.com"
        assertEquals(
            /* expected = */ false,
            /* actual = */ emailInput.isValidEmail(),
        )
    }

    // Priority to do: Low
    @Test
    fun email_isNotValid_evil() {
        val emailInput = "emailtestcom"
        assertNotEquals(
            /* unexpected = */ false.not(),
            /* actual = */ emailInput.isValidEmail(),
        )
    }
}
