package com.CallAPI.call.controller;


import com.CallAPI.call.Entity.User;
import com.CallAPI.call.model.JwtRequest;
import com.CallAPI.call.model.JwtResponse;
import com.CallAPI.call.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("test")
  public String test() {
    return "Test";
  }

  @PostMapping("register")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.saveUser(user));
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestBody JwtRequest jwtRequest)
      throws AuthenticationException {
    return ResponseEntity.ok(userService.authenticateUser(jwtRequest));
  }
}
