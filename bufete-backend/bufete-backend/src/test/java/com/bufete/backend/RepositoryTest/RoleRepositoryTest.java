package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;

import com.bufete.backend.model.Role;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.repository.RoleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

  @Autowired
  private RoleRepository roleRepository;

  private Role createRole() {
    Role role = new Role("Parent Role", StatusName.ACTIVE);
    role.setCreatedAt(Instant.now());
    role.setUpdatedAt(Instant.now());
    roleRepository.save(role);
    return role;
  }

  @Test
  public void testSaveRoles() {
    Role role = createRole();
    assertNotNull(role);
  }

  @Test
  public void testSaveRolesHierarchy() {

    Role parentRole = new Role("Parent Role", StatusName.ACTIVE);
    parentRole.setCreatedAt(Instant.now());
    parentRole.setUpdatedAt(Instant.now());
    roleRepository.save(parentRole);
    assertNotNull(parentRole);

    Role childRole = new Role("Child Role", StatusName.ACTIVE);
    childRole.setCreatedAt(Instant.now());
    childRole.setUpdatedAt(Instant.now());
    Role parent = roleRepository.findByName("Parent Role").get();
    childRole.setParent_Role(parent);
    roleRepository.save(childRole);

    assertNotNull(childRole);
    assertEquals(parent, childRole.getParent_Role());
  }

  @Test
  public void testGetRole() {
    Role role = createRole();
    Role role2 = roleRepository.findByName("Parent Role").get();
    assertNotNull(role);
    assertEquals(role2.getName(), role.getName());
  }

  @Test
  public void testDeleteRole() {
    Role role = createRole();
    roleRepository.delete(role);
  }

  @Test
  public void findAllRoles() {
    createRole();
    assertNotNull(roleRepository.findAll());
  }

  @Test
  public void deleteByRoleIdTest() {
    Role rol = createRole();
    roleRepository.deleteById(rol.getId());
  }
}