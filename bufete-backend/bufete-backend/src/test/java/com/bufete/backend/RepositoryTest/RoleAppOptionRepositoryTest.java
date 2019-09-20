package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.util.List;

import com.bufete.backend.model.AppOption;
import com.bufete.backend.model.Role;
import com.bufete.backend.model.RoleAppOption;
import com.bufete.backend.model.RoleOptionKey;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.repository.AppOptionRepository;
import com.bufete.backend.repository.RoleAppOptionRepository;
import com.bufete.backend.repository.RoleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleAppOptionRepositoryTest {

  @Autowired
  private RoleAppOptionRepository roleAppOptionRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AppOptionRepository appOptionRepository;

  private Role createRole(String roleName) {
    Role role = new Role(roleName, StatusName.ACTIVE);
    role.setCreatedAt(Instant.now());
    role.setUpdatedAt(Instant.now());
    roleRepository.save(role);
    return role;
  }

  private AppOption createAppOption(String optionName, String path) {
    AppOption option = new AppOption(optionName, path, StatusName.ACTIVE);
    option.setCreatedAt(Instant.now());
    option.setUpdatedAt(Instant.now());
    appOptionRepository.save(option);
    return option;
  }

  private RoleAppOption createRoleAppOption() {
    Role role = createRole("Test role");
    AppOption appOption = createAppOption("Test App", "/");

    RoleOptionKey id = new RoleOptionKey(role.getId(), appOption.getId());

    RoleAppOption roleAppOption = new RoleAppOption(id, role, appOption, StatusName.ACTIVE);
    roleAppOption.setCreatedAt(Instant.now());
    roleAppOption.setUpdatedAt(Instant.now());
    roleAppOptionRepository.save(roleAppOption);
    return roleAppOption;
  }

  @Test
  public void testCreateRoleAppOption() {
    RoleAppOption roleAppOption = createRoleAppOption();
    assertNotNull(roleAppOption);
  }

  @Test
  public void testObtainRoleAppOptionByRole() {
    RoleAppOption roleAppOption = createRoleAppOption();
    assertNotNull(roleAppOption);

    // Region: Create other appOption for the same role

    AppOption option = createAppOption("Test App2", "/");
    option.setCreatedAt(Instant.now());
    option.setUpdatedAt(Instant.now());
    // option.setStatus(StatusName.DELETED);
    appOptionRepository.save(option);

    RoleOptionKey id = new RoleOptionKey(roleAppOption.getRole().getId(), option.getId());

    RoleAppOption roleAppOption2 = new RoleAppOption(id, roleAppOption.getRole(), option, StatusName.ACTIVE);
    roleAppOption2.setCreatedAt(Instant.now());
    roleAppOption2.setUpdatedAt(Instant.now());
    roleAppOptionRepository.save(roleAppOption2);
    // EndRegion

    List<AppOption> optionList = roleAppOptionRepository.getAppOptionByRoleId(roleAppOption.getRole().getId());
    assertNotNull(optionList);
    assertEquals(optionList.size(), 2);
  }
}