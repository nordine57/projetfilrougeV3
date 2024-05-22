package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.Model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheDAO extends JpaRepository<Tache, Long> {
}
