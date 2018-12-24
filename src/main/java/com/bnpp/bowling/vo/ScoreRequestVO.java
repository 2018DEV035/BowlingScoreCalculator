package com.bnpp.bowling.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bnpp.bowling.dto.FrameDTO;

/**
 * VO to capture request values
 *
 */
public class ScoreRequestVO {

	@Size(min = 10, message = "Minimum of 10 frames are required to calculate the total score. Please verify your input")
	@Size(max = 11, message = "Not more than 11 frames are allowed for calculation. Please verify your input")
	@NotNull
	@Valid
	private List<FrameDTO> frames;

	public List<FrameDTO> getFrames() {
		return frames;
	}

	public void setFrames(List<FrameDTO> frames) {
		this.frames = frames;
	}

}
