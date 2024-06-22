package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.UserCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.UserLoginRequestDto;
import com.chainXpert.fin_manager.dto.request.UserPasswordUpdationRequestDto;
import com.chainXpert.fin_manager.service.UserService;
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
public class AuthenticationController {

    private final UserService userService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> userCreationHandler(@RequestBody final UserCreationRequestDto userCreationRequest) {
        return userService.createUser(userCreationRequest);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> userLoginHandler(
            @RequestBody(required = true) final UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PutMapping(value = "/update-password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> userPasswordUpdationHandler(
            @RequestBody(required = true) final UserPasswordUpdationRequestDto userPasswordUpdationRequestDto,
            @RequestHeader(name = "Authorization") final String token) {
        return userService.updatePassword(userPasswordUpdationRequestDto, token);
    }
}
