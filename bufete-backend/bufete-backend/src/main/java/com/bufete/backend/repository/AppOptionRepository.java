package com.bufete.backend.repository;

import java.util.Optional;

import com.bufete.backend.model.AppOption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppOptionRepository extends JpaRepository<AppOption, Long> {

  Optional<AppOption> findByName(String optionName);
}