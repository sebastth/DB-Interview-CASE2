package com.dbinterview.distanceCalculatorService.service.implementation

import com.dbinterview.distanceCalculatorService.model.Station
import com.dbinterview.distanceCalculatorService.repository.StationRepository
import com.dbinterview.distanceCalculatorService.service.StationService
import org.springframework.stereotype.Service

@Service
class DefaultStationService(val stationRepository: StationRepository) : StationService {

    override fun getStationsByDS100(ds100List: List<String>): Map<String, Station> = stationRepository.findAllById(ds100List).associateBy { it.ds100 }
}