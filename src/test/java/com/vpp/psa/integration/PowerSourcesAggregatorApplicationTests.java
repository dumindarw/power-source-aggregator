package com.vpp.psa.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@Testcontainers
class PowerSourcesAggregatorApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Container
	private static final MariaDBContainer MARIA_DB_CONTAINER = new MariaDBContainer("mariadb:10.6.5");

	@Test
	void api_should_create_multiple_batteries() throws Exception {

		String json;

		try(InputStream inputStream =Thread.currentThread().getContextClassLoader().getResourceAsStream("test-data.json")){
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonArr = mapper.readValue(inputStream ,
					JsonNode.class);
			//jsonArr.length();
			//Integer.parseInt(jsonArr.getJSONObject(0).getString("postcode"));

			json = mapper.writeValueAsString(jsonArr);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}

		String result = "{\"data\": {\"accepted\": 20, \"sent\": 20  },\"success\": true }";

		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
						.content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(result))
				.andReturn();
	}

	@Test
	void api_should_not_create_battery_with_null_attr() throws Exception {

		String testData = "[{\"name\": null ,\"postcode\": 6107, \"capacity\": 13500 }]";
		String result = "{\"data\": {\"accepted\": 0, \"sent\": 1  },\"success\": true }";

		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
						.content(testData)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andExpect(content().json(result))
				.andReturn();

	}



	@Test
	void api_should_retrieve_battery_name_list_acs_by_postcode_range() throws Exception {

		String result = "{\"data\": {\"batteries\": [\"SydneySt\", \"WestPC\"], \"average\": 5500 , \"sum\": 11000 },\"success\": true }";

		mockMvc.perform(get("/?postCodeFrom=2391&postCodeTo=2400").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(result))
				.andReturn();
	}

	@Test
	void api_should_not_retrieve_battery_name_list_if_invalid_postcode_range() throws Exception {
		String result = "{\"data\": {\"batteries\": [], \"average\": 0.0 , \"sum\": 0.0 },\"success\": true }";

		mockMvc.perform(get("/?postCodeFrom=491&postCodeTo=1000").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(result))
				.andReturn();
	}

}
