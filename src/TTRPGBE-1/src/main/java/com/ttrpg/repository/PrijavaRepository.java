package com.ttrpg.repository;

import com.ttrpg.model.Prijava;
import com.ttrpg.model.PrivatniKorisnik;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrijavaRepository extends JpaRepository<Prijava, Prijava.PrijavaId> {
    List<Prijava> findByIdGameId(long gameId);
    List<Prijava> findByIdUserId(long userId);
    long countByGameIdAndStatus(long gameId, String status);
    List<Prijava> findByGameIdAndPrivateUser(Long gameId, PrivatniKorisnik privateUser);
}
