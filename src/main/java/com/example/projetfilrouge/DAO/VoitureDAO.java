package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureDAO extends JpaRepository<Voiture, Long> {
}
