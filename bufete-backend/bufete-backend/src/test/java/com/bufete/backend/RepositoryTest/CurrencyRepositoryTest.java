package com.bufete.backend.RepositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;

import com.bufete.backend.model.Company;
import com.bufete.backend.model.Currency;
import com.bufete.backend.model.StatusName;
import com.bufete.backend.repository.CompanyRepository;
import com.bufete.backend.repository.CurrencyRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CurrencyRepositoryTest {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Autowired
  private CompanyRepository companyRepository;

  private Company createCompany() {
    Company company = new Company("Industria La Popular", StatusName.ACTIVE);
    company.setCreatedAt(Instant.now());
    company.setUpdatedAt(Instant.now());
    companyRepository.save(company);
    return company;
  }

  private Currency createCurrency() {
    Long userId = (long) 1;
    Company company = createCompany();
    Currency currency = new Currency("US Dollar", "US", 7.5, StatusName.ACTIVE, company);
    currency.setCreatedAt(Instant.now());
    currency.setUpdatedAt(Instant.now());
    currency.setCreatedBy(userId);
    currency.setUpdatedBy(userId);
    currencyRepository.save(currency);
    return currency;
  }

  @Test
  public void testCreateCurrency() {
    Currency Currency = createCurrency();
    assertNotNull(Currency);
  }

  @Test
  public void testGetConfig() {
    Currency currency = createCurrency();
    assertNotNull(currency);

    Currency currency2 = currencyRepository.findByName("US Dollar").get();
    assertNotNull(currency2);
    assertEquals(currency2.getName(), currency.getName());
  }

  @Test
  public void updateCurrency() {
    Currency currency = createCurrency();
    assertNotNull(currency);

    currency.setExchangeValue(7.56);
    currency.setUpdatedAt(Instant.now());
    currency.setUpdatedBy((long) 2);
    currencyRepository.save(currency);
    // assertEquals(currency.getExchangeValue(), Double.parseDouble("7.56"));
  }

  @Test
  public void changeStatusTest() {
    Currency currency = createCurrency();
    assertNotNull(currency);

    currency.setStatus(StatusName.DELETED);
    currency.setUpdatedAt(Instant.now());
    currency.setUpdatedBy((long) 2);
    currencyRepository.save(currency);
    assertEquals(currency.getStatus(), StatusName.DELETED);
  }

  @Test
  public void testFindAllCurrency() {
    Currency currency = createCurrency();
    assertNotNull(currency);

    assertNotNull(currencyRepository.findAll());
  }
}