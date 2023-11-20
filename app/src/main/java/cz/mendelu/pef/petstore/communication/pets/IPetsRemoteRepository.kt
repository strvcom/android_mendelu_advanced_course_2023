package cz.mendelu.pef.petstore.communication.pets

import cz.mendelu.pef.petstore.architecture.CommunicationResult
import cz.mendelu.pef.petstore.architecture.IBaseRemoteRepository
import cz.mendelu.pef.petstore.model.Pet

interface IPetsRemoteRepository : IBaseRemoteRepository {
    suspend fun pets(status: String): CommunicationResult<List<Pet>>
}