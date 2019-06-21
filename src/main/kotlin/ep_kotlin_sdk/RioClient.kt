/*
 * ****************************************************************************
 *   Copyright 2014-2019 Spectra Logic Corporation. All Rights Reserved.
 * ***************************************************************************
 */

package com.spectralogic.escapepod.devintegration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.spectralogic.retrofitutils.RetrofitClientFactoryImpl
import java.util.UUID
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.POST
import retrofit2.http.Path

interface RioClient {
    @DELETE("/api/cluster")
    suspend fun deleteCluster(): Unit

    @POST("/api/devices/spectra")
    suspend fun createSpectraDevice(@Body createSpectraDevice: CreateSpectraDevice): SpectraDeviceResponse

    @HEAD("/api/devices/spectra/{deviceName}")
    suspend fun headSpectraDevice(@Path("deviceName") name: String): Response<Void>

    @POST("/api/brokers")
    suspend fun createBroker(@Body createBroker: CreateBroker): Broker

    @HEAD("/api/brokers/{brokerName}")
    suspend fun headBroker(@Path("brokerName") brokerName: String): Response<Void>

    @DELETE("/api/brokers/{brokerName}")
    suspend fun deleteBroker(@Path("brokerName") brokerName: String): Unit

    @GET("/api/brokers/{brokerName}/objects")
    suspend fun listObjects(@Path("brokerName") brokerName: String): ListObjectMetadata

    @GET("/api/brokers/{brokerName}/objects/{objectName}")
    suspend fun getObjectMetadata(@Path("brokerName") brokerName: String, @Path("objectName") objectName: String): ObjectMetadata

    @POST("/api/brokers/{brokerName}/archive")
    suspend fun archiveFiles(@Path("brokerName") brokerName: String, @Body archiveFiles: FilesToArchive): RioJob

    @GET("/api/jobs/{jobId}")
    suspend fun getJobStatus(@Path("jobId") jobId: UUID): DetailedRioJob

    @DELETE("/api/brokers/{brokerName}/objects/{objName}")
    suspend fun deleteObject(brokerName: String, objName: String): Unit
}

fun createRioClient(endpoint: String, token: String): RioClient {
    return RetrofitClientFactoryImpl().createJsonRestClientWithBearer(
        endpoint,
        RioClient::class.java,
        "",
        "Rio-Dev-Integration",
        true,
        token,
        jacksonObjectMapper()
    )
}
