package cz.mendelu.pef.petstore.communication.pets

import cz.mendelu.pef.petstore.architecture.CommunicationResult
import cz.mendelu.pef.petstore.mock.ServerMock
import cz.mendelu.pef.petstore.model.Pet
import cz.mendelu.pef.petstore.useMockedValues
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PetsRemoteRepositoryImpl @Inject constructor(
    private val petsAPI: PetsAPI
) : IPetsRemoteRepository {

    override suspend fun pets(status: String): CommunicationResult<List<Pet>> {
        return when (useMockedValues) {
            true ->
                CommunicationResult.Success(
                    ServerMock.all
                )

            false ->
                processResponse(withContext(Dispatchers.IO) {
                    petsAPI.pets(status)
                })
        }
    }
}