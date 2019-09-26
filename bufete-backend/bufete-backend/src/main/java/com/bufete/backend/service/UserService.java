package com.bufete.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bufete.backend.exception.ResourceNotFoundException;
import com.bufete.backend.model.Company;
import com.bufete.backend.model.Role;
import com.bufete.backend.model.RoleAssign;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.model.User;
import com.bufete.backend.model.UserRoleCompanyKey;
import com.bufete.backend.payload.AssignRequest;
import com.bufete.backend.payload.CompanyResponse;
import com.bufete.backend.payload.SignUpRequest;
import com.bufete.backend.repository.CompanyRepository;
import com.bufete.backend.repository.RoleAssignRepository;
import com.bufete.backend.repository.RoleRepository;
import com.bufete.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private RoleRepository roleRepository;

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

  @Transactional
  public void registerUser(SignUpRequest signUpRequest) {
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
  }

  @Transactional
  public void assignRoles(AssignRequest assignRequest) {
    Optional<User> user = userRepository.findById(assignRequest.getUserId());
    if (!user.isPresent()) {
      throw new ResourceNotFoundException("User", "id", assignRequest.getUserId());
    }

    User userDetail = user.get();
    Company company = companyRepository.findById(assignRequest.getCompanyId()).get();

    for (Long item : assignRequest.getRolesId()) {
      Role role = roleRepository.findById(item).get();
      UserRoleCompanyKey id = new UserRoleCompanyKey(userDetail.getId(), role.getId(), company.getId());
      if (!roleAssignRepository.existsById(id)) {
        RoleAssign roleAssign = new RoleAssign(id, userDetail, role, company, StatusName.ACTIVE);
        roleAssignRepository.save(roleAssign);
      }
    }
  }

  public List<CompanyResponse> getCompaniesPerUser(Long userId) {
    List<Company> companyList = roleAssignRepository.getCompaniesPerUserId(userId);
    if (companyList.size() == 0) {
      throw new ResourceNotFoundException("company List", "user Id", userId);
    }

    List<CompanyResponse> responses = companyList.stream().map(company -> {
      return new CompanyResponse(company.getId(), company.getName(), company.getCreatedAt());
    }).collect(Collectors.toList());
    return responses;
  }
}