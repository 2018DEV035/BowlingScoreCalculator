package com.bnpp.bowling.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bnpp.bowling.dto.FrameDTO;
import com.bnpp.bowling.service.ScoreService;
import com.bnpp.bowling.service.domain.Frame;
import com.bnpp.bowling.vo.ScoreRequestVO;

public class ScoreServiceImpl implements ScoreService {

	private ArrayList<Frame> frames;
	private int totalScore;

	private static final int BONUS = 10;

	@Override
	public int calculateScore(ScoreRequestVO request) {
		initializeFrames(request.getFrames());
		for (int frameIndex = 0; frameIndex < 10; frameIndex++) {
			Frame frame = frames.get(frameIndex);
			if (frame.isStrike())
				totalScore += +BONUS + frames.get(frameIndex + 1).getTotal();
			else if (frame.isSpare())
				totalScore += +BONUS + frames.get(frameIndex + 1).getFirstRoll();
			else
				totalScore += +frame.getTotal();
		}
		return totalScore;
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

	}

}
