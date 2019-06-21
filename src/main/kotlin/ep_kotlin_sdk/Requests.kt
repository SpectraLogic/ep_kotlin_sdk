/*
 * ****************************************************************************
 *   Copyright 2014-2019 Spectra Logic Corporation. All Rights Reserved.
 * ***************************************************************************
 */

package com.spectralogic.escapepod.devintegration

import java.net.URI
import java.util.UUID

data class CreateSpectraDevice(
    val name: String,
    val mgmtInterface: String,
    val username: String,
    val password: String
)

data class FilesToArchive(val files: List<FileToArchive>)

data class FileToArchive(val name: String, val uri: URI, val size: Long)

data class CreateBroker(val name: String, val agentName: String, val agentConfig: BpAgentConfig)

sealed class AgentConfig

class BpAgentConfig(
    val bucket: String,
    val blackPearlName: String,
    val username: String,
    val createBucket: Boolean = false,
    val dataPolicyUUID: UUID? = null
) : AgentConfig()

data class UserLoginCredentials(val username: String, val password: String)
