package com.bnpp.bowling.vo;

public class ScoreResponseVO {

	private int score;
	private String errorDescription;

	public String getErrorMessage() {
		return errorDescription;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorDescription = errorMessage;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
