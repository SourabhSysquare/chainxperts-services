package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    @Query(value = "SELECT * FROM user_search_history WHERE user_id = :userId", nativeQuery = true)
    List<SearchHistory> findAllByUserId(Long userId);
}
