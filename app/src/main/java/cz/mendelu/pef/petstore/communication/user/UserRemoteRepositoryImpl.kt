package cz.mendelu.pef.petstore.communication.user

import cz.mendelu.pef.petstore.architecture.CommunicationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(
    private val userAPI: UserAPI
) : IUserRemoteRepository {

    override suspend fun login(
        username: String,
        password: String
    ): CommunicationResult<LoginResponse> =
        processResponse(withContext(Dispatchers.IO) {
            userAPI.login(username, password)
        })

}