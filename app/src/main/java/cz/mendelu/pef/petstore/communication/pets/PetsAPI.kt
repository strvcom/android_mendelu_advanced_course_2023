package cz.mendelu.pef.petstore.communication.pets

import cz.mendelu.pef.petstore.model.Pet
import retrofit2.Response
import retrofit2.http.*

interface PetsAPI {

    @Headers("Content-Type: application/json")
    @GET("pet/findByStatus")
    suspend fun pets(@Query("status") status: String): Response<List<Pet>>

}