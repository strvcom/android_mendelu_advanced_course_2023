package cz.mendelu.pef.petstore.communication.user

data class LoginResponse(
    val code: Int,
    val type: String,
    val message: String
)
