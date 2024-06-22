package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.BalanceModeCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.BalanceModeUpdationRequestDto;
import com.chainXpert.fin_manager.service.BalanceModeService;
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
@AllArgsConstructor
@RequestMapping(path = "/balance-mode")
public class BalanceModeController {

    private final BalanceModeService balanceModeService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> balanceModeCreationHandler(
            @RequestBody final BalanceModeCreationRequestDto balanceModeCreationRequestDto,
            @RequestHeader(name = "Authorization")  final String token) {
        return balanceModeService.create(balanceModeCreationRequestDto, token);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> balanceModeUpdationHandler(
            @RequestBody(required = true) final BalanceModeUpdationRequestDto balanceModeUpdationRequestDto,
            @RequestHeader(name = "Authorization")  final String token) {
        return balanceModeService.update(balanceModeUpdationRequestDto, token);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> balanceModeRetreivalHandler(
            @RequestHeader(name = "Authorization")  final String token) {
        return balanceModeService.retrieve(token);
    }
}
