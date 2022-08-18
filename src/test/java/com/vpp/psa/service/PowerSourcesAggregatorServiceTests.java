package com.vpp.psa.service;


import com.vpp.psa.model.Battery;
import com.vpp.psa.repository.BatteryRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@Testcontainers
class PowerSourcesAggregatorServiceTests {

	@Mock
	BatteryRepo batteryRepo;

	@InjectMocks
	BatteryService service;


	@Container
	private static final MariaDBContainer MARIA_DB_CONTAINER = new MariaDBContainer("mariadb:10.6.5");

	@Test
	void create_batteries_then_all_accepted() {

		List<Battery> batteryList = List.of(new Battery("Axs", 1234, 6600), new Battery("Zws", 1214, 6100));
		when(batteryRepo.createBattery(batteryList.get(0))).thenReturn(1);
		when(batteryRepo.createBattery(batteryList.get(1))).thenReturn(1);

		assertThat(service.createBatteries(batteryList)).containsKeys("sent", "accepted");
		assertThat(service.createBatteries(batteryList)).containsValues(2, 2);
	}

	@Test
	void get_batteries_by_postcode_range_then_the_stats() {


		when(batteryRepo.getBatteriesByPostcodeRange(2390, 2395).entrySet().stream()
				.collect(Collectors.toMap(e -> (String)e.getKey(), e -> (Double)e.getValue()))).thenReturn(Map.of("WestPC",5100.00, "SydneySt",5900.00));

		assertThat(service.getBatteriesByPostcodeRange(2390,2395)).containsKeys("batteries","sum","average");
		assertThat(service.getBatteriesByPostcodeRange(2390,2395)).containsValues(Set.of("SydneySt","WestPC"),11000.00,5500.00);
	}

}
