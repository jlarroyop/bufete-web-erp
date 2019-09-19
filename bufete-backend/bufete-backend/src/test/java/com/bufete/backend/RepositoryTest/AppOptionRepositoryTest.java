package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;

import com.bufete.backend.model.AppOption;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.repository.AppOptionRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppOptionRepositoryTest {

  @Autowired
  private AppOptionRepository appOptionRepository;

  private AppOption createAppOption() {
    AppOption option = new AppOption("users", "/users", StatusName.ACTIVE);
    option.setCreatedAt(Instant.now());
    option.setUpdatedAt(Instant.now());
    appOptionRepository.save(option);
    return option;
  }

  @Test
  public void testCreateOption() {
    AppOption option = createAppOption();
    assertNotNull(option);
  }

  @Test
  public void testGetOption() {
    AppOption option = createAppOption();
    assertNotNull(option);

    AppOption option2 = appOptionRepository.findByName("users").get();
    assertNotNull(option2);
    assertEquals(option2.getName(), option.getName());
  }

  @Test
  public void testUpdateOption() {
    AppOption option = createAppOption();
    assertNotNull(option);

    option.setPath("/user/:id");
    option.setUpdatedAt(Instant.now());
    appOptionRepository.save(option);
    assertEquals(option.getPath(), "/user/:id");
  }

  @Test
  public void testHierarchyOption() {
    AppOption optionParent = createAppOption();
    assertNotNull(optionParent);

    AppOption optionChild = new AppOption("user config", "/", StatusName.ACTIVE);
    optionChild.setParent_app_option(optionParent);
    optionChild.setCreatedAt(Instant.now());
    optionChild.setUpdatedAt(Instant.now());
    appOptionRepository.save(optionChild);
    assertEquals(optionChild.getParent_app_option(), optionParent);
  }

  @Test
  public void changeStatsTest() {
    AppOption option = createAppOption();
    assertNotNull(option);

    option.setStatus(StatusName.DELETED);
    option.setUpdatedAt(Instant.now());
    appOptionRepository.save(option);
    assertEquals(option.getStatus(), StatusName.DELETED);
  }

  @Test
  public void testFindAllOption() {
    AppOption option = createAppOption();
    assertNotNull(option);

    assertNotNull(appOptionRepository.findAll());
  }
}