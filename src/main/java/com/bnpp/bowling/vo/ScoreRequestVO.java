package com.bnpp.bowling.vo;

import java.util.List;

import com.bnpp.bowling.dto.FrameDTO;

public class ScoreRequestVO {

	private List<FrameDTO> frames;

	public List<FrameDTO> getFrames() {
		return frames;
	}

	public void setFrames(List<FrameDTO> frames) {
		this.frames = frames;
	}

}
