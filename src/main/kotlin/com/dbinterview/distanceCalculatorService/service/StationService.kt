package com.dbinterview.distanceCalculatorService.service

import com.dbinterview.distanceCalculatorService.model.Station
import com.dbinterview.distanceCalculatorService.service.exception.StationNotFoundException

interface StationService {

    @Throws(StationNotFoundException::class)
    fun getStation(ds100: String): Station
}