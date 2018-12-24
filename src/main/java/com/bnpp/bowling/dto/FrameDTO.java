package com.bnpp.bowling.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO to store each frame pin details for first and second roll
 *
 */
public class FrameDTO {

	public FrameDTO() {
	}

	@NotNull(message = "First Roll value should not be null. Please enter 0 for no pins")
	@Min(value = 0, message = "First Roll value should be greater than or equal to {value}")
	@Max(value = 10, message = "First Roll value should be lesser than or equal to {value}")
	private Integer firstRoll = -1;
	@NotNull(message = "Second Roll value should not be null. Please enter 0 for no pins")
	@Min(value = 0, message = "Second Roll value should be greater than or equal to {value}")
	@Max(value = 10, message = "Second Roll value should be lesser than or equal to {value}")
	private Integer secondRoll = -1;

	public Integer getFirstRoll() {
		return firstRoll;
	}

	public Integer getSecondRoll() {
		return secondRoll;
	}

	public FrameDTO(Integer firstRoll, Integer secondRoll) {
		this.firstRoll = firstRoll;
		this.secondRoll = secondRoll;
	}

}
