package com.ttrpg.repository;

import com.ttrpg.model.Prijava;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrijavaRepository extends JpaRepository<Prijava, Prijava.PrijavaId> {
    List<Prijava> findByPrijava(Prijava.PrijavaId prijava);
    long countByGameIdAndStatus(long gameId, String status);
}
