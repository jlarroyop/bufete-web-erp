package com.bufete.backend.service;

import com.bufete.backend.repository.RoleAssignRepository;
import com.bufete.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleAssignRepository roleAssignRepository;

  public boolean isAuthenticated(String username, String password, Long companyId) {
    return true;
  }
}