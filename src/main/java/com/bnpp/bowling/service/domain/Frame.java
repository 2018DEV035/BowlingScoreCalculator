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

}
