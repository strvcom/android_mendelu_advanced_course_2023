package cz.mendelu.pef.petstore

import cz.mendelu.pef.petstore.extension.isValidPassword
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class PasswordValidatorUnitTests {

    // Priority to do: High
    @Test
    fun password_isValid() {
        val passwordInput = "heslo123"
        assertEquals(
            /* expected = */ true,
            /* actual = */ passwordInput.isValidPassword(),
        )
    }

    // Priority to do: High
    @Test
    fun password_isNotValid_1() {
        val passwordInput = "heslo"
        assertEquals(
            /* expected = */ false,
            /* actual = */ passwordInput.isValidPassword(),
        )
    }

    // Priority to do: Low
    @Test
    fun password_isNotValid_2() {
        val passwordInput = "heslo1"
        assertEquals(
            /* expected = */ true,
            /* actual = */ passwordInput.isValidPassword().not(),
        )
    }

    // Priority to do: Low
    @Test
    fun password_isNotValid_3() {
        val passwordInput = "123456"
        assertNotEquals(
            /* unexpected = */ true,
            /* actual = */ passwordInput.isValidPassword(),
        )
    }

}
