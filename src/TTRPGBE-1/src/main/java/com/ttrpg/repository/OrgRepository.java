package com.ttrpg.repository;

import com.ttrpg.model.OrgProfil;
import com.ttrpg.model.PoslovniKorisnik;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgRepository extends JpaRepository<OrgProfil, Integer> {
    List<OrgProfil> findByBusinessUser(PoslovniKorisnik poslovniKorisnik);
}