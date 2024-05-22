package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.Model.ModeleVoiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeleDAO extends JpaRepository<ModeleVoiture, Long> {
}
