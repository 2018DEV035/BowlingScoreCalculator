package com.bnpp.bowling.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bnpp.bowling.dto.FrameDTO;
import com.bnpp.bowling.service.impl.ScoreServiceImpl;
import com.bnpp.bowling.vo.ScoreRequestVO;

public class ScoreServiceTest {

	private ScoreService scoreService;
	private ScoreRequestVO request;
	private List<FrameDTO> frames;

	@Before
	public void setUp() {

		scoreService = new ScoreServiceImpl();
		request = new ScoreRequestVO();
		frames = new ArrayList<>();
		request.setFrames(frames);
	}

	@After
	public void tearDown() {

		scoreService = null;
	}

	@Test
	public void testForGutterGame() {
		setFrames(10, 0, 0);
		assertEquals(0, scoreService.calculateScore(request));
	}

	@Test
	public void testForAllSinglePins() {
		setFrames(10, 1, 1);
		assertEquals(20, scoreService.calculateScore(request));
	}

	@Test
	public void testForFirstSpare() {
		setSpareInFrame();
		setFrames(9, 1, 1);
		assertEquals(29, scoreService.calculateScore(request));
	}

	private void setSpareInFrame() {
		frames.add(new FrameDTO(5, 5));
	}

	private void setFrames(int frameCount, int firstRoll, int secondRoll) {
		for (int i = 0; i < frameCount; i++) {
			frames.add(new FrameDTO(firstRoll, secondRoll));
		}
	}

}
