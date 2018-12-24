package com.bnpp.bowling.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnpp.bowling.service.ScoreService;
import com.bnpp.bowling.vo.ScoreRequestVO;
import com.bnpp.bowling.vo.ScoreResponseVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * ScoreController handles APIs to perform score calculation
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

	@Autowired
	private ScoreService service;
	private Logger logger = LogManager.getLogger(ScoreController.class);

	/**
	 * API to calculate the bowling score from input frame details
	 *
	 * @param scoreRequestVO
	 *            The pin details for all frames wrapped in list of FrameDTO.
	 * @param errors
	 *            Hidden parameter to fetch input validation errors.
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Calculate the total score for 10 pin bowling game.", response = ScoreResponseVO.class, responseContainer = "ResponseEntity", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Score successfully calculated."),
			@ApiResponse(code = 400, message = "Bad Request. Request input validation error."),
			@ApiResponse(code = 500, message = "Internal System Error. Returns any uncaught Exception message.") })
	@PostMapping(value = "/calculateScore", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScoreResponseVO> calculateScore(
			@RequestBody @Valid @ApiParam(value = "List of frame objects with min size 10 and max size 11") ScoreRequestVO scoreRequestVO,
			Errors errors) {
		logger.debug("calculateScore() API is invoked");
		ScoreResponseVO response = new ScoreResponseVO();
		HttpStatus status;
		try {
			if (errors.hasErrors()) {
				response.setErrorMessage(errors.getFieldError().getDefaultMessage());
				status = HttpStatus.BAD_REQUEST;
			} else {
				response.setScore(service.calculateScore(scoreRequestVO));
				status = HttpStatus.OK;
			}
		} catch (Exception ex) {
			response.setErrorMessage(ex.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		logger.debug("Operation completed with HTTP status code {}", +status.value());
		return new ResponseEntity<>(response, status);
	}

}
