package com.dbinterview.distanceCalculatorService.service.implementation

import com.dbinterview.distanceCalculatorService.config.Configuration
import com.dbinterview.distanceCalculatorService.model.Distance
import com.dbinterview.distanceCalculatorService.model.Station
import com.dbinterview.distanceCalculatorService.service.DistanceService
import com.dbinterview.distanceCalculatorService.service.StationService
import com.dbinterview.distanceCalculatorService.service.exception.StationNotFoundException
import com.dbinterview.distanceCalculatorService.service.helper.DistanceCalculatorHelper
import org.springframework.stereotype.Service

@Service
class DefaultDistanceService(val stationService: StationService) : DistanceService {


    override fun calculateDistance(fromDS100: String, toDS100: String): Distance {

        // get Stations
        val stations: Map<String, Station> = stationService.getStationsByDS100(listOf(fromDS100, toDS100))

        val fromStation: Station? = stations[fromDS100]
        val toStation: Station? = stations[toDS100]


        // calculate distance
        return if (fromStation != null && toStation != null) {
            val distanceValue = DistanceCalculatorHelper.calculateDistanceWithHaversineFormula(
                    fromStation.longitude,
                    fromStation.latitude,
                    toStation.longitude,
                    toStation.latitude)
            //return distance object
            Distance(fromStation.name, toStation.name, distanceValue, Configuration.unit)
        }
        else{
            throw StationNotFoundException("Unable to find stations with ids: $fromDS100, $toDS100!")
        }
    }

}
