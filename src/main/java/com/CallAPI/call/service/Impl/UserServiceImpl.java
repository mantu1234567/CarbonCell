package com.CallAPI.call.service.Impl;

import com.CallAPI.call.Entity.User;
import com.CallAPI.call.JwtToken.JwtService;
import com.CallAPI.call.model.JwtRequest;
import com.CallAPI.call.model.JwtResponse;
import com.CallAPI.call.respository.UserRepository;
import com.CallAPI.call.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public JwtResponse authenticateUser(JwtRequest jwtRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
            jwtRequest.getPassword()));
    if (authentication.isAuthenticated()) {
      String token = jwtService.genrateToken(jwtRequest.getUsername());
      return JwtResponse.builder().accessToken(token).build();
    } else {
      throw new UsernameNotFoundException("User Not Found");
    }
  }
}
