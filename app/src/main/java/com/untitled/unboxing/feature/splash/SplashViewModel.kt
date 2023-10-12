package com.untitled.unboxing.feature.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.untitled.unboxing.network.RetrofitUnboxingNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val retrofitUnboxingNetwork: RetrofitUnboxingNetwork
) : ViewModel() {

    private val _successLogin = MutableSharedFlow<Unit>()
    val successLogin = _successLogin.asSharedFlow()

    fun login(token: String) = viewModelScope.launch {
        kotlin.runCatching {
            retrofitUnboxingNetwork.login(token)
        }.onSuccess {
            _successLogin.emit(Unit)
        }.onFailure {
            Log.e("ERROR", it.message.toString(), )
        }
    }
}