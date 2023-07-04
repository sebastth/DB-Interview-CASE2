package com.dbinterview.distanceCalculatorService.service

import com.dbinterview.distanceCalculatorService.model.Station
import com.dbinterview.distanceCalculatorService.service.exception.StationNotFoundException


interface StationService {

    @Throws(StationNotFoundException::class)
    fun getStationsByDS100(ds100List: List<String>): Map<String, Station>
}