package com.example.randomuser.core.network

import com.example.randomuser.core.data.user.Users
import com.example.randomuser.core.utils.NetworkHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RandomUserRepository {

    fun getUsers(): Flow<Resource<Users>>

    class RandomUserRepositoryImpl
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: RandomUserService
    ) : RandomUserRepository {

        override fun getUsers(): Flow<Resource<Users>> {
            return flow {
                emit(Resource.loading(data = null))
                try {
                    val result = service.getUsers()
                    emit(Resource.success(data = result))
                } catch (exception: Exception) {
                    emit(
                        Resource.error(
                            data = null, message = exception.message
                                ?: "Error Occurred!"
                        )
                    )
                }
            }
        }
    }

}
