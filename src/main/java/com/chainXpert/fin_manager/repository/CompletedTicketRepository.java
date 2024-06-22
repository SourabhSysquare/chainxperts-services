package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.CompletedTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Repository
public interface CompletedTicketRepository extends JpaRepository<CompletedTicket, Long> {

    List<CompletedTicket> findByUserIdAndCreatedAtBetween(Long userId, LocalDate startDate, LocalDate endDate);

}
