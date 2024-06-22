package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.NoteCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.NoteUpdationRequestDto;
import com.chainXpert.fin_manager.service.NoteService;
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
@RequestMapping(value = "/note")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> noteCreationHandler(
            @RequestBody final NoteCreationRequestDto noteCreationRequest,
            @RequestHeader(name = "Authorization")  final String token) {
        return noteService.create(noteCreationRequest, token);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> noteUpdationHandler(
            @RequestBody final NoteUpdationRequestDto noteUpdationRequest,
            @RequestHeader(name = "Authorization")  final String token) {
        return noteService.update(noteUpdationRequest, token);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> noteRetreivalHandler(
            @RequestHeader(name = "Authorization")  final String token) {
        return noteService.retreive(token);
    }

}
