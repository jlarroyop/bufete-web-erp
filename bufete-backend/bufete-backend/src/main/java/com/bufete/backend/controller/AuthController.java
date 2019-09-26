package com.bufete.backend.controller;

import java.util.List;

import javax.validation.Valid;

import com.bufete.backend.payload.ApiResponse;
import com.bufete.backend.payload.CompanyResponse;
import com.bufete.backend.payload.JwtAuthenticationResponse;
import com.bufete.backend.payload.LoginRequest;
import com.bufete.backend.payload.SignUpRequest;
import com.bufete.backend.repository.UserRepository;
import com.bufete.backend.security.CustomAuthenticationToken;
import com.bufete.backend.security.JwtTokenProvider;
import com.bufete.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(new CustomAuthenticationToken(
        loginRequest.getUsernameOrEmail(), loginRequest.getPassword(), loginRequest.getCompanyId()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }

    userService.registerUser(signUpRequest);
    return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
  }

  @GetMapping("/companies/{userId}")
  public List<CompanyResponse> getCompaniesByUserId(@PathVariable Long userId) {
    return userService.getCompaniesPerUser(userId);
  }
}
