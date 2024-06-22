package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.TotalBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */

@Repository
public interface TotalBalanceRepository extends JpaRepository<TotalBalance, Long> {

    Optional<TotalBalance> findByUserId(Long userId);

}
