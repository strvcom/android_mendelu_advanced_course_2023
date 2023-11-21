package cz.mendelu.pef.petstore.communication.user

import cz.mendelu.pef.petstore.architecture.CommunicationResult
import cz.mendelu.pef.petstore.useMockedValues
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(
    private val userAPI: UserAPI
) : IUserRemoteRepository {

    override suspend fun login(
        username: String,
        password: String
    ): CommunicationResult<LoginResponse> {
        return when (useMockedValues) {
            true -> CommunicationResult.Success(
                LoginResponse(
                    code = 200,
                    message = "Success",
                    type = "type"
                )
            )

            false -> processResponse(withContext(Dispatchers.IO) {
                userAPI.login(username, password)
            })
        }
    }

}