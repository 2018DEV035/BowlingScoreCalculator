package com.bnpp.bowling.service;

import com.bnpp.bowling.vo.ScoreRequestVO;

/**
 * Interface for Scoring Service with calculateScore() method to calculate
 * scores
 */
public interface ScoreService {

	int calculateScore(ScoreRequestVO frames);

}
