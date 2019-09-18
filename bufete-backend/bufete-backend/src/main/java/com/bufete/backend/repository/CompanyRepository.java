package com.bufete.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bufete.backend.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Optional<Company> findByName(String name);
}
