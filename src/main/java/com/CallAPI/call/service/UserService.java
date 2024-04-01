package com.CallAPI.call.service;

import com.CallAPI.call.Entity.User;
import com.CallAPI.call.model.JwtRequest;
import com.CallAPI.call.model.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

  User saveUser(User user);

  JwtResponse authenticateUser(JwtRequest jwtRequest);
}
