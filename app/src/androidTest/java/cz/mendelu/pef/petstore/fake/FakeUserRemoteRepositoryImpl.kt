package cz.mendelu.pef.petstore.fake

import cz.mendelu.pef.petstore.architecture.CommunicationError
import cz.mendelu.pef.petstore.architecture.CommunicationResult
import cz.mendelu.pef.petstore.communication.user.IUserRemoteRepository
import cz.mendelu.pef.petstore.communication.user.LoginResponse
import javax.inject.Inject

class FakeUserRemoteRepositoryImpl @Inject constructor() : IUserRemoteRepository {

    override suspend fun login(
        username: String,
        password: String
    ): CommunicationResult<LoginResponse> {
        /*return CommunicationResult.Success(
            LoginResponse(
                code = 200,
                message = "Success",
                type = "type"
            )
        )*/
        return CommunicationResult.Error(
            CommunicationError(0, "")
        )
    }
}