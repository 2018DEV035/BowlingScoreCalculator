package com.bnpp.bowling.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bnpp.bowling.service.ScoreService;

@RunWith(SpringRunner.class)
public class ScoreControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ScoreService mockService;

	@InjectMocks
	private ScoreController scoreController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(scoreController).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	/**
	 * Test case to verify 200 valid response to get proper score for one pin each
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAllFramesOnes() throws Exception {
		String inputJson = "{  \"frames\": [    {      \"firstRoll\": 1,      \"secondRoll\": 1    },    {      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    }  ]}";
		when(mockService.calculateScore(any())).thenReturn(20);
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.score").value(20));
	}

	/**
	 * Test case to verify 400 Bad Request when input validation fails for pin count
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCalculateScoreForInvalidGreaterPins() throws Exception {
		String jsonInput = "{  \"frames\": [    {      \"firstRoll\": 11,      \"secondRoll\": 1    },    {      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    }  ]}";
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errorMessage").value("First Roll value should be lesser than or equal to 10"));
	}

	/**
	 * Test case to verify 400 Bad Request when input validation fails with only one
	 * frame
	 *
	 * @throws Exception
	 */
	@Test
	public void testCalculateScoreForBadRequestWithOnlyOneFrame() throws Exception {
		String jsonInput = "{  \"frames\": [    {      \"firstRoll\": 1,      \"secondRoll\": 1    }]}";
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
						"Minimum of 10 frames are required to calculate the total score. Please verify your input"));
	}

	/**
	 * Test case to verify 400 Bad Request when input validation fails with only
	 * null frame
	 *
	 * @throws Exception
	 */
	@Test
	public void testCalculateScoreForBadRequestWithNullFrame() throws Exception {
		String jsonInput = "{  \"frames\": [  ]}";
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
						"Minimum of 10 frames are required to calculate the total score. Please verify your input"));
	}

	/**
	 * Test case to verify 400 Bad Request when input validation fails for more than
	 * 11 frames
	 *
	 * @throws Exception
	 */
	@Test
	public void testCalculateScoreForBadRequestWithMoreThanElevenFrames() throws Exception {
		String jsonInput = "{  \"frames\": [    {      \"firstRoll\": 0,      \"secondRoll\": 1    },    {      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    }  ]}";
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage")
						.value("Not more than 11 frames are allowed for calculation. Please verify your input"));
	}

	/**
	 * Test case to verify 400 Bad Request when input validation fails with pin
	 * value as null
	 *
	 * @throws Exception
	 */
	@Test
	public void testCalculateScoreForBadRequestWithPinAsNull() throws Exception {
		String jsonInput = "{  \"frames\": [    {      \"firstRoll\": 0,      \"secondRoll\": 1    },    {      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": null    }  ]}";
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage")
						.value("Second Roll value should not be null. Please enter 0 for no pins"));
	}

	/**
	 * Test case to validate Any uncaught Exception in the service for 500 Internal
	 * Server Error
	 *
	 * @throws Exception
	 */
	@Test
	public void testForInternalError() throws Exception {
		String jsonInput = "{  \"frames\": [    {      \"firstRoll\": 1,      \"secondRoll\": 1    },    {      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    }  ]}";
		when(mockService.calculateScore(any())).thenThrow(new ArrayIndexOutOfBoundsException("Error occurred"));
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.errorMessage").value("Error occurred"));
	}

	/**
	 * Test case to validate invalid API URL for 404 error
	 *
	 * @throws Exception
	 */
	@Test
	public void testForApiNotFound() throws Exception {
		String jsonInput = "{}";
		this.mockMvc.perform(post("/score/score").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
				.andExpect(status().isNotFound());
	}

}
