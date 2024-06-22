package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.GoalCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.GoalUpdationRequestDto;
import com.chainXpert.fin_manager.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@RestController
@RequestMapping(value = "/goal")
@AllArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> goalCreationHandler(
            @RequestBody(required = true) final GoalCreationRequestDto goalCreationRequest,
            @RequestHeader(name = "Authorization")  final String token) {
        return goalService.create(goalCreationRequest, token);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> goalUpdationHandler(
            @RequestBody(required = true) final GoalUpdationRequestDto goalUpdationRequest,
            @RequestHeader(name = "Authorization")  final String token) {
        return goalService.update(goalUpdationRequest, token);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> goalsRetreivalHandler(
            @RequestHeader(name = "Authorization")  final String token) {
        return goalService.retreive(token);
    }

}
