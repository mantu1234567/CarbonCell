package com.CallAPI.call.security;

import com.CallAPI.call.Entity.User;
import com.CallAPI.call.respository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> userInfo = userRepository.findByUsername(username);
    return userInfo.map(UserInfoUserDetails::new)
        .orElseThrow(() -> new RuntimeException("user not found " + username));
  }

}
