package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.Voiture;
import com.example.projetfilrouge.Model.VoitureOccasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoitureOccasionDao extends JpaRepository<VoitureOccasion, Long> {







}
