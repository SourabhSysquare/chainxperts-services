package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.CurrentMonthlySpendingThresholdLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Repository
public interface CurrentMonthlySpendingThresholdLimitRepository
        extends JpaRepository<CurrentMonthlySpendingThresholdLimit, Integer> {

    Optional<CurrentMonthlySpendingThresholdLimit> findByUserId(UUID userId);

}
