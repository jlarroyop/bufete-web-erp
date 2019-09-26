package com.bufete.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bufete.backend.model.Company;
import com.bufete.backend.model.Role;
import com.bufete.backend.model.RoleAssign;
import com.bufete.backend.model.StatusName;
// import com.bufete.backend.exception.AppException;
// import com.bufete.backend.model.Role;
import com.bufete.backend.model.User;
import com.bufete.backend.model.UserRoleCompanyKey;
import com.bufete.backend.payload.ApiResponse;
import com.bufete.backend.payload.JwtAuthenticationResponse;
import com.bufete.backend.payload.LoginRequest;
import com.bufete.backend.payload.SignUpRequest;
import com.bufete.backend.repository.CompanyRepository;
import com.bufete.backend.repository.RoleAssignRepository;
import com.bufete.backend.repository.RoleRepository;
import com.bufete.backend.repository.UserRepository;
import com.bufete.backend.security.CustomAuthenticationToken;
import com.bufete.backend.security.JwtTokenProvider;
import javax.validation.Valid;
import java.net.URI;
// import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private RoleAssignRepository roleAssignRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

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

    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
        signUpRequest.getPassword(), StatusName.ACTIVE);

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    User userDetail = userRepository.save(user);
    Company company = companyRepository.findById(signUpRequest.getCompanyId()).get();

    for (Long item : signUpRequest.getRolesId()) {
      Role role = roleRepository.findById(item).get();
      UserRoleCompanyKey id = new UserRoleCompanyKey(userDetail.getId(), role.getId(), company.getId());
      RoleAssign roleAssign = new RoleAssign(id, userDetail, role, company, StatusName.ACTIVE);
      roleAssignRepository.save(roleAssign);
    }

    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
        .buildAndExpand(userDetail.getUsername()).toUri();

    return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
  }
}
