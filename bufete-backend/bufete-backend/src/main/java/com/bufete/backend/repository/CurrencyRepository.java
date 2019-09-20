package com.bufete.backend.repository;

import java.util.Optional;

import com.bufete.backend.model.Currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  Optional<Currency> findByName(String concurrencyName);
}