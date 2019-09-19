package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

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

  private Company createCompany() {
    Long userId = (long) 1;
    Company company = new Company("Industria La Popular", StatusName.ACTIVE);
    company.setCreatedAt(Instant.now());
    company.setUpdatedAt(Instant.now());
    company.setCreatedBy(userId);
    company.setUpdatedBy(userId);
    companyRepository.save(company);
    return company;
  }

  @Test
  public void testCreateCompany() {
    Company company = createCompany();
    assertNotNull(company);
  }

  @Test
  public void testGetCompany() {
    Company company = createCompany();
    assertNotNull(company);

    Company company2 = companyRepository.findByName("Industria La Popular").get();
    assertNotNull(company2);
    assertEquals(company2.getName(), company.getName());
  }

  @Test
  public void testDeleteCompany() {
    Company company = createCompany();
    companyRepository.delete(company);
  }

  @Test
  public void testFindAllCompanies() {
    createCompany();
    assertNotNull(companyRepository.findAll());
  }

  @Test
  public void deleteByCompanyIdTest() {
    Company company = createCompany();
    companyRepository.deleteById(company.getId());
  }
}