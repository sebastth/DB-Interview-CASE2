package com.dbinterview.distanceCalculatorService.repository

import com.dbinterview.distanceCalculatorService.model.Station
import org.springframework.stereotype.Repository

import org.springframework.data.repository.CrudRepository


@Repository
interface StationRepository : CrudRepository<Station, String>