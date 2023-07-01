package com.dbinterview.distanceCalculatorService.service.helper

import com.dbinterview.distanceCalculatorService.config.Configuration
import kotlin.math.*

public object DistanceCalculatorHelper {
    public fun calculateDistanceWithHaversineFormula(lon1: Double,
                                                     lat1: Double,
                                                     lon2: Double,
                                                     lat2: Double): Int{

        // Convert latitude and longitude from degrees to radians
        val lon1Rad = Math.toRadians(lon1)
        val lat1Rad = Math.toRadians(lat1)
        val lon2Rad = Math.toRadians(lon2)
        val lat2Rad = Math.toRadians(lat2)

        // Calculate difference between latitudes and longitudes
        val lonDiff = lon2Rad - lon1Rad
        val latDiff = lat2Rad - lat1Rad

        // Haversine formula
        val a = sin(latDiff / 2).pow(2) + cos(lat1Rad) * cos(lat2Rad) * sin(lonDiff / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        // Calculate the distance
        val distance = Configuration.earthRadius * c

        // Round number and return
        return distance.roundToInt()

    }
}