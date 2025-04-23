package com.assignment.repository;

import com.assignment.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version, Integer> {
    Optional<Version> findByName(String name);
}
