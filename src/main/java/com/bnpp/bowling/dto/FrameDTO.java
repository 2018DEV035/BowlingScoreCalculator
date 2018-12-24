package com.bnpp.bowling.dto;

public class FrameDTO {

	private int firstRoll = -1;
	private int secondRoll = -1;

	public int getFirstRoll() {
		return firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public FrameDTO(int firstRoll, int secondRoll) {
		this.firstRoll = firstRoll;
		this.secondRoll = secondRoll;
	}

}
