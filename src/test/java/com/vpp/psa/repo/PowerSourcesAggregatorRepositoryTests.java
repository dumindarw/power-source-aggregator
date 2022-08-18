package com.vpp.psa.repo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpp.psa.model.Battery;
import com.vpp.psa.repository.BatteryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@Testcontainers
class PowerSourcesAggregatorRepositoryTests {

	@Autowired
	BatteryRepo batteryRepo;


	@Container
	private static final MariaDBContainer MARIA_DB_CONTAINER = new MariaDBContainer("mariadb:10.6.5");

	@Test
	void create_batteries() {
		Integer createdId = batteryRepo.createBattery(new Battery("Axs", 1234, 6600));

		assertThat(createdId).isEqualTo(1);
	}

	@Test
	void get_batteries_by_postcodeRange(){
		Map<String,Double> batteries =  batteryRepo.getBatteriesByPostcodeRange(2390, 2395).entrySet().stream()
				.collect(Collectors.toMap(e -> (String)e.getKey(), e -> (Double)e.getValue()));

		assertThat(batteries).containsKey("WestPC");
		assertThat(batteries).containsValue(5100.00);
	}

}
