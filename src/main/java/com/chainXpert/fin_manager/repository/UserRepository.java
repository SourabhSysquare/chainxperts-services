package com.chainXpert.fin_manager.repository;

import com.chainXpert.fin_manager.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);
}
