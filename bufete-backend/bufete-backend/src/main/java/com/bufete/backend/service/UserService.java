package com.bufete.backend.service;

import java.util.Optional;

import com.bufete.backend.model.User;
import com.bufete.backend.repository.RoleAssignRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private RoleAssignRepository roleAssignRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public boolean isAuthenticated(String username, String password, Long companyId) {
    Optional<User> user = roleAssignRepository.findUserByUsernameAndCompany(username, companyId);
    if (!user.isPresent())
      return false;
    User userDetail = user.get();
    return passwordEncoder.matches(password, userDetail.getPassword());
  }
}