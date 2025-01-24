package com.ttrpg.repository;

import com.ttrpg.model.Pitanje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PitanjeRepository extends JpaRepository<Pitanje, Pitanje.PitanjeId> {
    public List<Pitanje> findByIdGameId(Long id);
    public List<Pitanje> findByGameIdAndQuestionText(Long gameId, String questionText);
}
