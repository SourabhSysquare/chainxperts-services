package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.response.TagRetreivalRequestDto;
import com.chainXpert.fin_manager.service.TagService;
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
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> tagRetreivalHandler(
            @RequestBody TagRetreivalRequestDto tagRetreivalRequest,
            @RequestHeader(name = "Authorization") final String token) {
        return tagService.retrieve(tagRetreivalRequest, token);
    }
}
