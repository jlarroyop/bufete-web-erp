package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.time.Instant;
import java.util.List;

import com.bufete.backend.model.Company;
import com.bufete.backend.model.Role;
import com.bufete.backend.model.RoleAssign;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.model.User;
import com.bufete.backend.model.UserRoleCompanyKey;
import com.bufete.backend.repository.CompanyRepository;
import com.bufete.backend.repository.RoleAssignRepository;
import com.bufete.backend.repository.RoleRepository;
import com.bufete.backend.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleAssignRepositoryTest {

  @Autowired
  private RoleAssignRepository roleAssignRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private RoleRepository roleRepository;

  private User createUser() {
    User user = new User("Jorge Arroyo", "jarroyo", "jlarroyop@gmail.com", "SiPues00$$.", StatusName.ACTIVE);
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    userRepository.save(user);
    return user;
  }

  private Company createCompany() {
    Company company = new Company("Industria La Popular", StatusName.ACTIVE);
    company.setCreatedAt(Instant.now());
    company.setUpdatedAt(Instant.now());
    companyRepository.save(company);
    return company;
  }

  private Role createRole() {
    Role role = new Role("Parent Role", StatusName.ACTIVE);
    role.setCreatedAt(Instant.now());
    role.setUpdatedAt(Instant.now());
    roleRepository.save(role);
    return role;
  }

  private RoleAssign createRoleAssign() {
    User user = createUser();
    Role role = createRole();
    Company company = createCompany();

    UserRoleCompanyKey id = new UserRoleCompanyKey(user.getId(), role.getId(), company.getId());

    RoleAssign roleAssign = new RoleAssign(id, user, role, company, StatusName.ACTIVE);
    roleAssign.setCreatedAt(Instant.now());
    roleAssign.setUpdatedAt(Instant.now());
    roleAssignRepository.save(roleAssign);
    return roleAssign;
  }

  @Test
  public void testCreateRoleAssign() {
    RoleAssign roleAssign = createRoleAssign();
    assertNotNull(roleAssign);
  }

  @Test
  public void testObtainRolesCountByUserAndCompany() {
    RoleAssign roleAssign = createRoleAssign();
    long count = roleAssignRepository.countRolesByUserIdAndCompanyId(roleAssign.getUser().getId(),
        roleAssign.getCompany().getId());
    assertEquals(count, 1);
  }

  @Test
  public void testObtainRolesByUserAndCompany() {
    RoleAssign roleAssign = createRoleAssign();

    // Region: Create other role for the same user in the same company
    Role role = new Role("Test Role", StatusName.ACTIVE);
    role.setCreatedAt(Instant.now());
    role.setUpdatedAt(Instant.now());
    roleRepository.save(role);

    UserRoleCompanyKey id = new UserRoleCompanyKey(roleAssign.getUser().getId(), role.getId(),
        roleAssign.getCompany().getId());

    RoleAssign roleAssign2 = new RoleAssign(id, roleAssign.getUser(), role, roleAssign.getCompany(), StatusName.ACTIVE);
    roleAssign2.setCreatedAt(Instant.now());
    roleAssign2.setUpdatedAt(Instant.now());
    roleAssignRepository.save(roleAssign2);
    // EndRegion: Create other role for the same user in the same company

    List<Role> roles = roleAssignRepository.getRolesByUserIdAndCompanyId(roleAssign.getUser().getId(),
        roleAssign.getCompany().getId());
    assertNotNull(roles);
    assertEquals(roles.size(), 2);
  }
}