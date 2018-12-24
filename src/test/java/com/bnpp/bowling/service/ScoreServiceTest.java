package com.bnpp.bowling.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bnpp.bowling.service.impl.ScoreServiceImpl;

public class ScoreServiceTest {

	private ScoreService scoreService;

	@Before
	public void setUp() {

		scoreService = new ScoreServiceImpl();
	}

	@After
	public void tearDown() {

		scoreService = null;
	}

	@Test
	public void test() {
		assertEquals(0, scoreService.calculateScore(null));
	}

}
