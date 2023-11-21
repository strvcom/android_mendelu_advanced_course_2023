package cz.mendelu.pef.petstore.fake

import cz.mendelu.pef.petstore.architecture.CommunicationResult
import cz.mendelu.pef.petstore.communication.pets.IPetsRemoteRepository
import cz.mendelu.pef.petstore.mock.ServerMock
import cz.mendelu.pef.petstore.model.Pet
import javax.inject.Inject

class FakePetsRemoteRepositoryImpl @Inject constructor() : IPetsRemoteRepository {

    override suspend fun pets(status: String): CommunicationResult<List<Pet>> {
        //  Use this for success fake response
        return CommunicationResult.Success(
            ServerMock.all
        )

        //  Use this for error fake response
        /*return CommunicationResult.Error(
            CommunicationError(0, "")
        )*/
    }
}