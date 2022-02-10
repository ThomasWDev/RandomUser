package com.example.randomuser.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.randomuser.core.base.BaseViewModel
import com.example.randomuser.core.data.user.Users
import com.example.randomuser.core.network.RandomUserRepository
import com.example.randomuser.core.network.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val randomUserRepository: RandomUserRepository,
) : BaseViewModel() {


    private val _users = MutableLiveData<Users>()
    val users: LiveData<Users> = _users

    init {

        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            randomUserRepository.getUsers()
                .collect { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            setLoading(false)
                            _users.value = resource.data!!
                        }
                        Status.ERROR -> {
                            setLoading(false)
                            setError(resource.message.toString())
                        }
                        Status.LOADING -> {
                            setLoading(true)
                        }
                    }
                }

        }
    }

}