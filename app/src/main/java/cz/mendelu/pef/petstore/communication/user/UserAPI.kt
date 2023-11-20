package cz.mendelu.pef.petstore.communication.user

import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    @Headers("Content-Type: application/json")
    @GET("user/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String): Response<LoginResponse>

}