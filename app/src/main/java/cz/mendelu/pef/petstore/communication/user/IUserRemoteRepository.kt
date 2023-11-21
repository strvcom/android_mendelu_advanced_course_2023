package cz.mendelu.pef.petstore.communication.user

import cz.mendelu.pef.petstore.architecture.CommunicationResult
import cz.mendelu.pef.petstore.architecture.IBaseRemoteRepository

interface IUserRemoteRepository : IBaseRemoteRepository {
    suspend fun login(
        username: String,
        password: String
    ): CommunicationResult<LoginResponse>
}