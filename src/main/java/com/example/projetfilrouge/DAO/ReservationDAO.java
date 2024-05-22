package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {
}
