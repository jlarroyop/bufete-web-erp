package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;

import com.bufete.backend.model.StatusName;
import com.bufete.backend.model.User;
import com.bufete.backend.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void testSaveUser() {
    User user = new User("Jorge Arroyo", "jarroyo", "jlarroyop@gmail.com", "SiPues00$$.", StatusName.ACTIVE);
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    userRepository.save(user);
    assertNotNull(user);
  }

  @Test
  public void testGetUser(){
    User user = new User("Jorge Arroyo", "jarroyo", "jlarroyop@gmail.com", "SiPues00$$.", StatusName.ACTIVE);
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    userRepository.save(user);
    assertNotNull(user);

    User user2 = userRepository.findByUsername(user.getUsername()).get();
    assertEquals(user.getName(), user2.getName());
  }

  @Test
  public void testDeleteRole() {
    User user = new User("Jorge Arroyo", "jarroyo", "jlarroyop@gmail.com", "SiPues00$$.", StatusName.ACTIVE);
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    userRepository.save(user);
    userRepository.delete(user);
  }

  @Test
  public void findAllUsers() {
    User user = new User("Jorge Arroyo", "jarroyo", "jlarroyop@gmail.com", "SiPues00$$.", StatusName.ACTIVE);
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    userRepository.save(user);
    assertNotNull(userRepository.findAll());
  }

  @Test
  public void deleteByUserIdTest() {
    User user = new User("Jorge Arroyo", "jarroyo", "jlarroyop@gmail.com", "SiPues00$$.", StatusName.ACTIVE);
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    User user2 = userRepository.save(user);
    userRepository.deleteById(user2.getId());
  }
}