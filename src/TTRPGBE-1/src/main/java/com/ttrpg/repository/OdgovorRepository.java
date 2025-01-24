package com.ttrpg.repository;

import com.ttrpg.model.Odgovor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdgovorRepository extends JpaRepository<Odgovor, Odgovor.OdgovorId> {
}
