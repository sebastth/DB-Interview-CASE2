package com.dbinterview.distanceCalculatorService.service.implementation

import com.dbinterview.distanceCalculatorService.config.Configuration
import com.dbinterview.distanceCalculatorService.model.Distance
import com.dbinterview.distanceCalculatorService.service.DistanceService
import com.dbinterview.distanceCalculatorService.service.StationService
import com.dbinterview.distanceCalculatorService.service.helper.DistanceCalculatorHelper
import org.springframework.stereotype.Service

@Service
class DefaultDistanceService(val stationService: StationService) : DistanceService {

    override fun calculateDistance(fromDS100: String, toDS100: String): Distance {

        // get Stations
        val fromStation = stationService.getStation(fromDS100)
        val toStation = stationService.getStation(toDS100)

        // calculate distance
        val distanceValue = DistanceCalculatorHelper.calculateDistanceWithHaversineFormula(
                fromStation.longitude,
                fromStation.latitude,
                toStation.longitude,
                toStation.latitude)

        //return distance object
        return Distance(fromStation.name, toStation.name, distanceValue, Configuration.unit)

    }

}
