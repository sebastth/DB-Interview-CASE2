package com.dbinterview.distanceCalculatorService.service

import com.dbinterview.distanceCalculatorService.model.Distance

interface DistanceService {
    fun calculateDistance(fromDS100: String, toDS100: String): Distance
}