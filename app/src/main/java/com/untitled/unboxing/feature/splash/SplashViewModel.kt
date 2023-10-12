package com.untitled.unboxing.feature.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.untitled.unboxing.network.RetrofitUnboxingNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val retrofitUnboxingNetwork: RetrofitUnboxingNetwork
) : ViewModel() {

    fun login(token: String) = viewModelScope.launch {
        kotlin.runCatching {
            retrofitUnboxingNetwork.login(token)
        }.onSuccess {

        }.onFailure {
            Log.e("ERROR", it.message.toString(), )
        }
    }
}