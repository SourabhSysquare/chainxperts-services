package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.FutureTicketCreationRequestDto;
import com.chainXpert.fin_manager.service.FutureTicketService;
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
@RequestMapping(path = "/future-ticket")
public class FutureTicketController {

    private final FutureTicketService futureTicketService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> futureTicketCreationHandler(
            @RequestBody(required = true) final FutureTicketCreationRequestDto futureTicketCreationRequestDto,
            @RequestHeader(name = "Authorization")  final String token) {
        return futureTicketService.create(futureTicketCreationRequestDto, token);
    }

    @DeleteMapping(value = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> futureTicketDeletionHandler(
            @PathVariable(name = "ticketId") final Long ticketId,
            @RequestHeader(name = "Authorization")  final String token) {
        return futureTicketService.delete(ticketId, token);
    }

    @GetMapping(value = "/expense", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> futureExpenseTicketRetreivalHandler(
            @RequestHeader(name = "Authorization")  final String token) {
        return futureTicketService.retreiveExpenses(token);
    }

    @GetMapping(value = "/gain", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> futureGainTicketRetreivalHandler(
            @RequestHeader(name = "Authorization")  final String token) {
        return futureTicketService.retreiveGains(token);
    }

}
