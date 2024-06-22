package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.dto.request.NoteCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.NoteUpdationRequestDto;
import com.chainXpert.fin_manager.dto.response.NoteDto;
import com.chainXpert.fin_manager.enitity.Note;
import com.chainXpert.fin_manager.enitity.NoteTagMapping;
import com.chainXpert.fin_manager.enitity.Tag;
import com.chainXpert.fin_manager.repository.NoteRepository;
import com.chainXpert.fin_manager.repository.NoteTagMappingRepository;
import com.chainXpert.fin_manager.repository.TagRepository;
import com.chainXpert.fin_manager.repository.UserRepository;
import com.chainXpert.fin_manager.security.utility.JwtUtils;
import com.chainXpert.fin_manager.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    private final TagRepository tagRepository;

    private final NoteTagMappingRepository noteTagMappingRepository;

    private final JwtUtils jwtUtils;

    private final ResponseUtils responseUtils;

    public ResponseEntity<?> create(final NoteCreationRequestDto noteCreationRequest, final String token) {
        final var note = new Note();
        note.setActive(true);
        note.setDescription(noteCreationRequest.getDescription());
        note.setTitle(noteCreationRequest.getTitle());
        note.setUserId(jwtUtils.extractUserId(token.replace("Bearer ", "")));

        final var savedNote = noteRepository.save(note);

        noteCreationRequest.getTags().forEach(noteTag -> {
            final var tag = tagRepository.findByName(noteTag.getName().toUpperCase()).orElse(new Tag());
            tag.setName(noteTag.getName().toUpperCase());
            final var savedTag = tagRepository.save(tag);

            final var noteTagMapping = new NoteTagMapping();
            noteTagMapping.setTagId(savedTag.getId());
            noteTagMapping.setNoteId(savedNote.getId());
            noteTagMappingRepository.save(noteTagMapping);
        });

        return responseUtils.noteSuccessResponse(savedNote.getId());
    }

    public ResponseEntity<?> update(final NoteUpdationRequestDto noteUpdationRequest, final String token) {
        final var note = noteRepository.findById(noteUpdationRequest.getId()).get();
        note.setActive(noteUpdationRequest.getIsActive());
        note.setDescription(noteUpdationRequest.getDescription());

        final var savedNote = noteRepository.save(note);

        noteUpdationRequest.getTags().forEach(noteTag -> {
            final var tag = tagRepository.findByName(noteTag.getName().toUpperCase()).orElse(new Tag());
            tag.setName(noteTag.getName().toUpperCase());
            final var savedTag = tagRepository.save(tag);

            final var noteTagMapping = noteTagMappingRepository
                    .findByTagIdAndNoteId(savedTag.getId(), savedNote.getId()).orElse(new NoteTagMapping());
            noteTagMapping.setTagId(savedTag.getId());
            noteTagMapping.setNoteId(savedNote.getId());
            noteTagMappingRepository.save(noteTagMapping);
        });

        return responseUtils.noteSuccessResponse(savedNote.getId());
    }

    public ResponseEntity<?> retreive(final String token) {
        final var user = userRepository.findById(jwtUtils.extractUserId(token.replace("Bearer ", ""))).get();
        return ResponseEntity.ok(user.getNotes().parallelStream()
                .map(note -> NoteDto.builder().id(note.getId()).createdAt(note.getCreatedAt())
                        .description(note.getDescription()).isActive(note.isActive()).title(note.getTitle())
                        .updatedAt(note.getUpdatedAt()).build())
                .collect(Collectors.toList()));
    }

}
