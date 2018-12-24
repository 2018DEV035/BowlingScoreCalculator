package com.bnpp.bowling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnpp.bowling.service.ScoreService;
import com.bnpp.bowling.vo.ScoreRequestVO;
import com.bnpp.bowling.vo.ScoreResponseVO;

@RestController
@RequestMapping("/score")
public class ScoreController {

	@Autowired
	private ScoreService service;

	@PostMapping(value = "/calculateScore", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScoreResponseVO> calculateScore(@RequestBody ScoreRequestVO scoreRequestVO) {
		ScoreResponseVO response = new ScoreResponseVO();
		HttpStatus status;
		response.setScore(service.calculateScore(scoreRequestVO));
		status = HttpStatus.OK;
		return new ResponseEntity<>(response, status);
	}

}
