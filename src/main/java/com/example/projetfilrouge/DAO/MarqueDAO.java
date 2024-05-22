package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueDAO extends JpaRepository<MarqueVoiture, Long> {
}
