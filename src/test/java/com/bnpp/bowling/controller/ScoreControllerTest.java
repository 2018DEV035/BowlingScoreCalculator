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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bnpp.bowling.service.ScoreService;

@RunWith(SpringRunner.class)
@WebMvcTest(ScoreController.class)
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

	@Test
	public void testAllFramesOnes() throws Exception {
		String inputJson = "{  \"frames\": [    {      \"firstRoll\": 1,      \"secondRoll\": 1    },    {      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    },{      \"firstRoll\": 1,      \"secondRoll\": 1    }  ]}";
		when(mockService.calculateScore(any())).thenReturn(20);
		this.mockMvc.perform(post("/score/calculateScore").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.score").value(20));
	}

}
