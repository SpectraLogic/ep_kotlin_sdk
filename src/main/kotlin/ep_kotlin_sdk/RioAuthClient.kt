/*
 * ****************************************************************************
 *   Copyright 2014-2019 Spectra Logic Corporation. All Rights Reserved.
 * ***************************************************************************
 */

package com.spectralogic.escapepod.devintegration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.spectralogic.retrofitutils.RetrofitClientFactoryImpl
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RioAuthClient {
    @GET("/api/cluster")
    suspend fun getCluster(): Response<ClusterResponse>

    @POST("/api/cluster")
    suspend fun createCluster(@Query("name") name: String): ClusterResponse

    @POST("/api/tokens")
    suspend fun createToken(@Body credentials: UserLoginCredentials): TokenResponse
}

fun createRioAuthClient(endpoint: String): RioAuthClient {
    return RetrofitClientFactoryImpl().createJsonRestClient(
        endpoint,
        RioAuthClient::class.java,
        "",
        "Rio-Dev-Integration",
        true,
        jacksonObjectMapper()
    )
}

fun createRioAuthClientFromEnv(): RioAuthClient {
    val escapePodUrl = System.getenv("ESCAPEPOD_URL")
    return createRioAuthClient(escapePodUrl)
}
