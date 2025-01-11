package com.ttrpg.repository;

import com.ttrpg.model.OrgProfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepository extends JpaRepository<OrgProfil, Integer> {
}