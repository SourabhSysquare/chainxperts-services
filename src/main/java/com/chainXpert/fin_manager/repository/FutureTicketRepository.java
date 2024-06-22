package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.FutureTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Repository
public interface FutureTicketRepository extends JpaRepository<FutureTicket, Long> {

}