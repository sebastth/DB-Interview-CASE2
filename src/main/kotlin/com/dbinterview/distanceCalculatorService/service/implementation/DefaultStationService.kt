package com.dbinterview.distanceCalculatorService.service.implementation

import com.dbinterview.distanceCalculatorService.model.Station
import com.dbinterview.distanceCalculatorService.repository.StationRepository
import com.dbinterview.distanceCalculatorService.service.StationService
import com.dbinterview.distanceCalculatorService.service.exception.StationNotFoundException
import org.springframework.stereotype.Service

@Service
class DefaultStationService(val stationRepository: StationRepository) : StationService {

    override fun getStation(ds100: String): Station = stationRepository.findById(ds100).orElseThrow {
        StationNotFoundException("Unable to find actor for $ds100 ds100")
    }
}