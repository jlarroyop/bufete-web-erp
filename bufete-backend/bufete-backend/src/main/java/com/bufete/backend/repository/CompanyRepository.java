package com.bufete.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.bufete.backend.model.Company;
import com.bufete.backend.model.StatusName;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Optional<Company> findByName(String name);

	Page<Company> findByStatus(StatusName status, Pageable pageable);
}
