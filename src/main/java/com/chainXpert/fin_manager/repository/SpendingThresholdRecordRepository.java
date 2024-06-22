package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.SpendingThresholdRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Repository
public interface SpendingThresholdRecordRepository extends JpaRepository<SpendingThresholdRecord, Integer> {

    List<SpendingThresholdRecord> findByUserId(Long userId);

}
