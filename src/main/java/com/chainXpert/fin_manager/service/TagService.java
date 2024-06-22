package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.contant.ContextType;
import com.chainXpert.fin_manager.dto.response.TagRetreivalRequestDto;
import com.chainXpert.fin_manager.enitity.Tag;
import com.chainXpert.fin_manager.repository.NoteTagMappingRepository;
import com.chainXpert.fin_manager.repository.TagRepository;
import com.chainXpert.fin_manager.repository.TicketTagMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    private final NoteTagMappingRepository noteTagMappingRepository;

    private final TicketTagMappingRepository ticketTagMappingRepository;

    public List<Tag> retreiveAll() {
        return tagRepository.findAll();
    }

    public ResponseEntity<List<String>> retrieve(final TagRetreivalRequestDto tagRetreivalRequest, final String token) {
        if (tagRetreivalRequest.getContextType().equalsIgnoreCase(ContextType.NOTE.getName())) {
            return ResponseEntity
                    .ok(noteTagMappingRepository.findByNoteId(tagRetreivalRequest.getContextId()).parallelStream()
                            .map(noteTagMapping -> noteTagMapping.getTag().getName()).collect(Collectors.toList()));
        }
        if (tagRetreivalRequest.getContextType().equalsIgnoreCase(ContextType.COMPLETED_TICKET.getName())
                || tagRetreivalRequest.getContextType().equalsIgnoreCase(ContextType.FUTURE_TICKET.getName()))
            return ResponseEntity
                    .ok(ticketTagMappingRepository.findByTicketId(tagRetreivalRequest.getContextId()).parallelStream()
                            .map(ticketTagMapping -> ticketTagMapping.getTag().getName()).collect(Collectors.toList()));
        return ResponseEntity.badRequest().build();

    }

}
