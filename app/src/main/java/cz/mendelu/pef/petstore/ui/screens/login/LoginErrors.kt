package cz.mendelu.pef.petstore.ui.screens.login

data class LoginErrors(
    val communicationError: Int? = null,
    val usernameError: String? = null,
    val passwordError: String? = null,
)
