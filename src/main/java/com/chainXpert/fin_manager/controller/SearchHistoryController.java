package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.NoteUpdationRequestDto;
import com.chainXpert.fin_manager.dto.request.SearchHistoryDto;
import com.chainXpert.fin_manager.service.SearchHistoryService;
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
@RequestMapping(path = "/search-history")
public class SearchHistoryController {

    private SearchHistoryService searchHistoryService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void upsertSearchHistory(
            @RequestBody final SearchHistoryDto searchHistoryDto,
            @RequestHeader(name = "Authorization")  final String token) {
         searchHistoryService.upsert(searchHistoryDto, token);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getUserAllSearchHistory(@RequestHeader(name = "Authorization")  final String token) {
        return searchHistoryService.getUserData(token);
    }
}
