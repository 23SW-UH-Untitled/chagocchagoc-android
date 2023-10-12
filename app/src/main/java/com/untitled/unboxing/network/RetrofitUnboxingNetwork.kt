package com.untitled.unboxing.network

import com.untitled.unboxing.model.TokenResponse
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Singleton

private interface RetrofitUnboxingNetworkApi {
    @POST("auth/google")
    fun login(
        @Query("token") token: String,
    ): TokenResponse
}

@Singleton
class RetrofitUnboxingNetwork(
    retrofit: Retrofit,
) : RetrofitUnboxingNetworkApi {
    private val unboxingApi = retrofit.create(RetrofitUnboxingNetworkApi::class.java)

    override fun login(token: String): TokenResponse =
        unboxingApi.login(token)
}