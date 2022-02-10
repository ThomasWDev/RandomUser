package com.example.randomuser.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.randomuser.core.base.BaseViewModel
import com.example.randomuser.core.data.user.Location
import com.example.randomuser.core.data.user.Users
import com.example.randomuser.core.network.RandomUserRepository
import com.example.randomuser.core.network.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
) : BaseViewModel() {

    private val _details = MutableLiveData<com.example.randomuser.core.data.user.Result>()
    val details: LiveData<com.example.randomuser.core.data.user.Result> = _details

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    fun setDetails(details: com.example.randomuser.core.data.user.Result?) {
        details?.let {
            _details.value = it
            _address.value = Location.getLocation(it.location)
        }
    }

}