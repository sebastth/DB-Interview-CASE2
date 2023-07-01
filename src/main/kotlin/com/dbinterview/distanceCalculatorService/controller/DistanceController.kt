package com.dbinterview.distanceCalculatorService.controller

import com.dbinterview.distanceCalculatorService.service.DistanceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/distance")
class DistanceController(val distanceService: DistanceService) {
    @GetMapping(value = ["/{fromDS100}/{toDS100}"])
    @ResponseStatus(HttpStatus.OK)
    private fun calculateDistance(@PathVariable fromDS100: String, @PathVariable toDS100: String): com.dbinterview.distanceCalculatorService.model.Distance {
        return distanceService.calculateDistance(fromDS100, toDS100)
    }

}