package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.Box;
import com.example.projetfilrouge.Model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxDAO extends JpaRepository<Box, Long> {
}
