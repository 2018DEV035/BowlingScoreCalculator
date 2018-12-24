package com.bnpp.bowling.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.bnpp.bowling.dto.FrameDTO;
import com.bnpp.bowling.service.ScoreService;
import com.bnpp.bowling.service.domain.Frame;
import com.bnpp.bowling.vo.ScoreRequestVO;

@Service
@RequestScope
public class ScoreServiceImpl implements ScoreService {

	private ArrayList<Frame> frames;
	private int totalScore;

	private static final int BONUS = 10;
	private static final int GAMES_FRAME_SIZE = 10;
	private static final int BONUS_FRAME_SIZE = 11;

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
		}
		return totalScore;

	}

	private void addScoreForSpare(int frameIndex) {
		totalScore += BONUS + frames.get(frameIndex + 1).getFirstRoll();
	}

	private void addScoreForStrike(int frameIndex) {
		totalScore += BONUS + frames.get(frameIndex + 1).getTotal();
		if ((frameIndex + 2 <= frames.size() - 1)
				&& (frameIndex + 1 == GAMES_FRAME_SIZE || frames.get(frameIndex + 1).isStrike())) {
			totalScore += frames.get(frameIndex + 2).getFirstRoll();
		}
	}

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
			frames.add(new Frame(bonusFrame.getFirstRoll(), 0));
			frames.add(new Frame(bonusFrame.getSecondRoll(), 0));
		}

	}

}
