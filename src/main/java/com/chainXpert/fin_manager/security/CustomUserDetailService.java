package com.chainXpert.fin_manager.security;

import com.chainXpert.fin_manager.security.utility.SecurityUtils;
import com.chainXpert.fin_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */

@Service
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*", value = "*")
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        return SecurityUtils.convert(userRepository.findByEmailId(emailId));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
