package com.bnpp.bowling.service.domain;

public class Frame {

	private int firstRoll;
	private int secondRoll;

	public Frame(int firstRoll, int secondRoll) {
		this.firstRoll = firstRoll;
		this.secondRoll = secondRoll;
	}

	public int getTotal() {
		return firstRoll + secondRoll;
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public boolean isSpare() {
		return firstRoll + secondRoll == 10;
	}

	public boolean isStrike() {
		return firstRoll == 10;
	}

}
