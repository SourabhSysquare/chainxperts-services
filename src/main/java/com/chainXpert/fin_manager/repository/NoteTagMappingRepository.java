package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.NoteTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Repository
public interface NoteTagMappingRepository extends JpaRepository<NoteTagMapping, Integer> {

    List<NoteTagMapping> findByNoteId(Long noteId);

    Optional<NoteTagMapping> findByTagIdAndNoteId(Integer tagId, Long noteId);

}
