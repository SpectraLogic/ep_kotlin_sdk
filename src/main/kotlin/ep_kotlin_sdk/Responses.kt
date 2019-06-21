/*
 * ****************************************************************************
 *   Copyright 2014-2019 Spectra Logic Corporation. All Rights Reserved.
 * ***************************************************************************
 */

package com.spectralogic.escapepod.devintegration

import java.util.UUID

data class ClusterResponse(val clusterName: String)

data class SpectraDeviceResponse(val name: String, val username: String, val mgmtInterface: String)

open class RioJob(
    val id: UUID,
    val creationDate: String,
    val lastUpdated: String,
    val status: RioJobStatus,
    val jobType: RioJobType,
    val numberOfFiles: Long,
    val filesTransferred: Long,
    val totalSizeInBytes: Long,
    val progress: Float
)

class DetailedRioJob(
    id: UUID,
    creationDate: String,
    lastUpdated: String,
    status: RioJobStatus,
    jobType: RioJobType,
    numberOfFiles: Long,
    filesTransferred: Long,
    totalSizeInBytes: Long,
    progress: Float,
    val files: List<FileStatus>
) : RioJob(
    id,
    creationDate,
    lastUpdated,
    status,
    jobType,
    numberOfFiles,
    filesTransferred,
    totalSizeInBytes,
    progress
)

data class FileStatus(val name: String, val status: String, val statusMessage: String)

enum class RioJobType {
    ARCHIVE, RESTORE, MIGRATION
}

data class RioJobStatus(val message: String, val status: String, val reason: String? = null)

data class Broker(val name: String)

data class ObjectMetadata(val name: String, val size: Long, val creationDate: String)

data class ListObjectMetadata(val objects: List<ObjectMetadata>)

data class TokenResponse(val token: String)