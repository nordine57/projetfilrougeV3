package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.Garage;
import com.example.projetfilrouge.Model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageDAO extends JpaRepository<Garage, Integer> {
}
