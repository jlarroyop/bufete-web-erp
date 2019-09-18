package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertNotNull;

import java.time.Instant;

import com.bufete.backend.model.Company;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.repository.CompanyRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

  @Autowired
  private CompanyRepository companyRepository;

  @Test
  public void testCreateCompany() {
    Long userId = (long) 1;
    Company company = new Company("Industria La Popular", StatusName.ACTIVE);
    company.setCreatedAt(Instant.now());
    company.setUpdatedAt(Instant.now());
    company.setCreatedBy(userId);
    company.setUpdatedBy(userId);
    companyRepository.save(company);
    assertNotNull(company);
  }
}