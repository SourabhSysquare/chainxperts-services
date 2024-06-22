package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.CompletedTicketCreationRequestDto;
import com.chainXpert.fin_manager.service.CompletedTicketService;
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
@RequestMapping(path = "/completed-ticket")
public class CompletedTicketController {

    private final CompletedTicketService completedTicketService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> completedTicketCreationHandler(
            @RequestBody(required = true) final CompletedTicketCreationRequestDto completedTicketCreationRequest,
            @RequestHeader(name = "Authorization")  final String token) {
        return completedTicketService.create(completedTicketCreationRequest, token);
    }

    @DeleteMapping(value = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> completedTicketDeletionHandler(
            @PathVariable(name = "ticketId") final Long ticketId,
            @RequestHeader(name = "Authorization")  final String token) {
        return completedTicketService.delete(ticketId, token);
    }

    @GetMapping(value = "/expense", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> completedExpenseTicketRetreivalHandler(
            @RequestHeader(name = "Authorization")  final String token) {
        return completedTicketService.retreiveExpenses(token);
    }

    @GetMapping(value = "/gain", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> completedGainTicketRetreivalHandler(
            @RequestHeader(name = "Authorization")  final String token) {
        return completedTicketService.retrieveGains(token);
    }

}
