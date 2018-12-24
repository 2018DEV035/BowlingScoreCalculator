package com.bnpp.bowling.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.bnpp.bowling.dto.FrameDTO;
import com.bnpp.bowling.service.ScoreService;
import com.bnpp.bowling.service.domain.Frame;
import com.bnpp.bowling.vo.ScoreRequestVO;

/**
 * To calculate score for 10-pin American Bowling. This Program assumes all
 * frame detail inputs are valid. Implements ScoringService interface
 */
@Service
@RequestScope
public class ScoreServiceImpl implements ScoreService {

	private ArrayList<Frame> frames;
	private int totalScore;

	private static final int BONUS = 10;
	private static final int GAMES_FRAME_SIZE = 10;
	private static final int BONUS_FRAME_SIZE = 11;
	private Logger logger = LogManager.getLogger(ScoreServiceImpl.class);

	/**
	 * Calculates the total score based on input of all frames
	 *
	 * @param request
	 *            Input with list of FrameDTO values
	 * @return totalScore
	 */
	@Override
	public int calculateScore(ScoreRequestVO request) {
		initializeFrames(request.getFrames());
		for (int frameIndex = 0; frameIndex < GAMES_FRAME_SIZE; frameIndex++) {
			Frame frame = frames.get(frameIndex);
			if (frame.isStrike()) {
				addScoreForStrike(frameIndex);
			} else if (frame.isSpare()) {
				addScoreForSpare(frameIndex);
			} else {
				totalScore += frame.getTotal();
			}
			logger.debug("Total score from frame {} is {}", frameIndex + 1, totalScore);
		}
		return totalScore;

	}

	/**
	 * Adds bonus score if the frame scores a spare
	 *
	 * @param frameIndex
	 *            Current index of iteration of total frame size
	 */
	private void addScoreForSpare(int frameIndex) {
		if ((frameIndex + 1 <= frames.size() - 1)) {
			totalScore += BONUS + frames.get(frameIndex + 1).getFirstRoll();
		}
		logger.debug("Spare Bonus score added for frame index {} and total score is {}", frameIndex, totalScore);
	}

	/**
	 * Adds bonus score if the frame scores a strike
	 *
	 * @param frameIndex
	 *            Current index of iteration of total frame size
	 */
	private void addScoreForStrike(int frameIndex) {
		if (frameIndex + 1 <= frames.size() - 1)
			totalScore += BONUS + frames.get(frameIndex + 1).getTotal();
		if ((frameIndex + 2 <= frames.size() - 1)
				&& (frameIndex + 1 == GAMES_FRAME_SIZE || frames.get(frameIndex + 1).isStrike())) {
			totalScore += frames.get(frameIndex + 2).getFirstRoll();
		}
		logger.debug("Strike Bonus score added for frame index {} and total score is {}", frameIndex, totalScore);
	}

	/**
	 * Method to initialize each frame values from controller and values for bonus
	 * rolls in separate frames (if exists)
	 *
	 * @param framesRequestList
	 *            Convert from VO to DTO frame object
	 */
	private void initializeFrames(List<FrameDTO> framesList) {
		totalScore = 0;
		frames = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			if (framesList.get(index) != null) {
				FrameDTO frameDTO = framesList.get(index);
				frames.add(new Frame(frameDTO.getFirstRoll(), frameDTO.getSecondRoll()));
			}
		}
		if (framesList.size() == BONUS_FRAME_SIZE) {
			FrameDTO bonusFrame = framesList.get(BONUS_FRAME_SIZE - 1);
			logger.debug("Bonus frame exists. Setting up bonus roll details with values {} and {}",
					bonusFrame.getFirstRoll(), bonusFrame.getSecondRoll());
			frames.add(new Frame(bonusFrame.getFirstRoll(), 0));
			frames.add(new Frame(bonusFrame.getSecondRoll(), 0));
		}

	}

}
