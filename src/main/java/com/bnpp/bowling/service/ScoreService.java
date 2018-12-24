package com.bnpp.bowling.service;

import com.bnpp.bowling.vo.ScoreRequestVO;

public interface ScoreService {

	int calculateScore(ScoreRequestVO frames);

}
