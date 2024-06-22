package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.TicketTagMapping;
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
public interface TicketTagMappingRepository extends JpaRepository<TicketTagMapping, Integer> {

    Optional<TicketTagMapping> findByTagIdAndTicketId(Integer tagId, Long ticketId);
    List<TicketTagMapping> findByTicketId(Long ticketId);

}
