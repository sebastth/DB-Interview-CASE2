package com.dbinterview.distanceCalculatorService.repository

import com.dbinterview.distanceCalculatorService.model.Station
import com.redis.testcontainers.RedisContainer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName


@DataRedisTest
@Testcontainers
class StationRepositoryTest{


    @Autowired
    private lateinit var stationRepository: StationRepository

    //Test stations
    private var station1: Station = Station("AAAAA", "Test1", "1", "Test1", "FV", 1.1, 1.1, "TestOperator1", "123", "TestStatus1")

    companion object {
        @Container
        private val redisContainer = RedisContainer(DockerImageName.parse("redis:latest"))

        @JvmStatic
        @DynamicPropertySource
        fun redisProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.redis.repositories.enabled") { true }
            registry.add("spring.data.redis.host") { redisContainer.host }
            registry.add("spring.data.redis.port") { redisContainer.firstMappedPort }
        }
    }
    @BeforeEach
    fun setUp() {
        stationRepository.deleteAll()
    }

    @Test
    fun `should find all Stations by ds100`() {
        val stationDS100 = createStation(station1)

        val result = stationRepository.findAllById(listOf(stationDS100)).associateBy { it.ds100 }

        val resultStation: Station? = result[station1.ds100]

        if(resultStation == null){
            fail("Station not retrieved by DS100")
        }
        else {
            assertEquals(station1.ds100, resultStation.ds100)
            assertEquals(station1.evaNr, resultStation.evaNr)
            assertEquals(station1.ifopt, resultStation.ifopt)
            assertEquals(station1.name, resultStation.name)
            assertEquals(station1.traffic, resultStation.traffic)
            assertEquals(station1.longitude, resultStation.longitude)
            assertEquals(station1.latitude, resultStation.latitude)
            assertEquals(station1.operatorName, resultStation.operatorName)
            assertEquals(station1.operatorNr, resultStation.operatorNr)
            assertEquals(station1.status, resultStation.status)
        }
    }


    private fun createStation(station: Station): String {
        val result = stationRepository.save(station)
        return result.ds100
    }
}