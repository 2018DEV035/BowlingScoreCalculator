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
		frames = null;
		request = null;
	}

	/**
	 * testForGutterGame() -,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,-,- --> 0
	 */
	@Test
	public void testForGutterGame() {
		setFrames(10, 0, 0);
		assertEquals(0, scoreService.calculateScore(request));
	}

	/**
	 * testForAllSinglePins() 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 --> 20
	 */
	@Test
	public void testForAllSinglePins() {
		setFrames(10, 1, 1);
		assertEquals(20, scoreService.calculateScore(request));
	}

	/**
	 * testForFirstSpare() 5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 --> 29
	 */
	@Test
	public void testForFirstSpare() {
		setSpareInFrame();
		setFrames(9, 1, 1);
		assertEquals(29, scoreService.calculateScore(request));
	}

	/**
	 * testForMiddleSpare() 1,1,1,1,1,1,1,1,5,5,1,1,1,1,1,1,1,1,1,1 --> 29
	 */
	@Test
	public void testForMiddleSpare() {
		setFrames(4, 1, 1);
		setSpareInFrame();
		setFrames(5, 1, 1);
		assertEquals(29, scoreService.calculateScore(request));
	}

	/**
	 * testForFirstStrike() X,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 --> 30
	 */
	@Test
	public void testForFirstStrike() {
		setStrikeInFrame();
		setFrames(9, 1, 1);
		assertEquals(30, scoreService.calculateScore(request));
	}

	/**
	 * testForLastSpare() 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,1 --> 29
	 */
	@Test
	public void testForLastSpare() {
		setFrames(9, 1, 1);
		setSpareInFrame();
		setBonusFrame(1, 0);
		assertEquals(29, scoreService.calculateScore(request));
	}

	/**
	 * testForLastStrike() 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,X,1,1 --> 30
	 */
	@Test
	public void testForLastStrike() {
		setFrames(9, 1, 1);
		setStrikeInFrame();
		setBonusFrame(1, 1);
		assertEquals(30, scoreService.calculateScore(request));
	}

	/**
	 * testForConsecutiveSpare() 1,1,5,5,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1 --> 42
	 */
	@Test
	public void testForConsecutiveSpare() {
		setFrames(1, 1, 1);
		setSpareInFrame();
		setSpareInFrame();
		setFrames(7, 1, 1);
		assertEquals(42, scoreService.calculateScore(request));
	}

	/**
	 * testForConsecutiveStrike() 1,1,10,0,10,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1 --> 49
	 */
	@Test
	public void testForConsecutiveStrike() {
		setFrames(1, 1, 1);
		setStrikeInFrame();
		setStrikeInFrame();
		setFrames(7, 1, 1);
		assertEquals(49, scoreService.calculateScore(request));
	}

	/**
	 * testAllSpare() 5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5 --> 150
	 */
	@Test
	public void testAllSpare() {
		setFrames(10, 5, 5);
		setBonusFrame(5, 0);
		assertEquals(150, scoreService.calculateScore(request));
	}

	/**
	 * testAllStrike() X,X,X,X,X,X,X,X,X,X,X,X --> 300
	 */
	@Test
	public void testAllStrike() {
		setFrames(10, 10, 0);
		setBonusFrame(10, 10);
		assertEquals(300, scoreService.calculateScore(request));
	}

	/**
	 * testFinalStrikeWithNoBonusFrame() 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,X -->
	 * 18
	 */
	@Test
	public void testFinalStrikeWithNoBonusFrame() {
		setFrames(9, 1, 1);
		setFrames(1, 10, 0);
		assertEquals(18, scoreService.calculateScore(request));
	}

	/**
	 * testFinalSpareWithNoBonusFrame() 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5 -->
	 * 18
	 */
	@Test
	public void testFinalSpareWithNoBonusFrame() {
		setFrames(9, 1, 1);
		setFrames(1, 5, 5);
		assertEquals(18, scoreService.calculateScore(request));
	}

	private void setSpareInFrame() {
		frames.add(new FrameDTO(5, 5));
	}

	private void setStrikeInFrame() {
		frames.add(new FrameDTO(10, 0));
	}

	private void setBonusFrame(int firstRoll, int secondRoll) {
		frames.add(new FrameDTO(firstRoll, secondRoll));
	}

	private void setFrames(int frameCount, int firstRoll, int secondRoll) {
		for (int i = 0; i < frameCount; i++) {
			frames.add(new FrameDTO(firstRoll, secondRoll));
		}
	}

}
