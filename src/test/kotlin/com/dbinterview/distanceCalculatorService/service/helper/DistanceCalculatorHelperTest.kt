package com.dbinterview.distanceCalculatorService.service.helper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DistanceCalculatorHelperTest{

    private var distanceCalculatorHelper = DistanceCalculatorHelper

    @Test
    fun `should calculate distance between Frankfurt Hbf and Berlin Hbf`() {
        val ffLongitude: Double = 8.663789
        val ffLatitude: Double = 50.107145
        val blsLongitude: Double = 13.369545
        val blsLatitude: Double = 52.525592

        val expectedResult: Int = 423

        val result = distanceCalculatorHelper.calculateDistanceWithHaversineFormula(ffLongitude,
                ffLatitude, blsLongitude, blsLatitude)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `distance between the same station should be zero`() {
        val longitude: Double = 8.663789
        val latitude: Double = 50.107145

        val expectedResult: Int = 0

        val result = distanceCalculatorHelper.calculateDistanceWithHaversineFormula(longitude,
                latitude, longitude, latitude)

        assertEquals(expectedResult, result)
    }
}