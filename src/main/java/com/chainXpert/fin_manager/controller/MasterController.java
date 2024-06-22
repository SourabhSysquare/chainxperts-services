package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.service.TagService;
import com.chainXpert.fin_manager.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/master")
public class MasterController {

    private final ResponseUtils responseUtils;

    private final TagService tagService;

    @GetMapping("/health-check/ping")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> serviceHealthCheckerHandler() {
        return responseUtils.pingResponse();
    }

    @GetMapping("/ticket-type")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> ticketTypesRetreivalHandler() {
        return responseUtils.ticketTypeListResponse();
    }

    @GetMapping("/balance-mode-type")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> balanceModeTypeRetreivalHandler() {
        return responseUtils.balanceModeTypeListResponse();
    }

    @GetMapping("/context-type-for-tags")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> contextTypeRetreivalHandler() {
        return responseUtils.contextTypeListResponse();
    }

    @GetMapping("/tags")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> tagsRetreivalHandler() {
        return ResponseEntity
                .ok(tagService.retreiveAll().parallelStream().map(tag -> tag.getName()).collect(Collectors.toList()));
    }

}
