package com.dbinterview.distanceCalculatorService.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("station")
data class Station(
        @Id val ds100: String,
        val evaNr: String,
        val ifopt: String,
        val name: String,
        val traffic: String,
        val longitude: Double,
        val latitude: Double,
        val operatorName: String,
        val operatorNr: String,
        val status: String
        )