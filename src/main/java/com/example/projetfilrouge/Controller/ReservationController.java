package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.ReservationDAO;
import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Reservation;
import com.example.projetfilrouge.Model.Utilisateur;
import com.example.projetfilrouge.Model.Voiture;
import com.example.projetfilrouge.securiter.AppUserDetails;
import com.example.projetfilrouge.securiter.IsAdmin;
import com.example.projetfilrouge.securiter.IsUser;
import com.example.projetfilrouge.view.ReservationView;
import com.example.projetfilrouge.view.UtilisateurAvecCommandeView;
import com.example.projetfilrouge.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    ReservationDAO reservationDAO;


    @GetMapping("/reservation/liste")
    @JsonView(UtilisateurView.class)
    public List<Reservation> liste() {
        return reservationDAO.findAll();
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> get(@PathVariable long id) {

        Optional<Reservation> reservationOptional = reservationDAO.findById(id);

        if(reservationOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservationOptional.get(),HttpStatus.OK);
    }


    @JsonView(ReservationView.class)
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> addVoiture(@RequestBody Reservation reservation, @AuthenticationPrincipal AppUserDetails user) {
        reservation.setReservation(user.getUtilisateur());
        reservationDAO.save(reservation);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }



    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Reservation> delete (@PathVariable long id) {

        Optional<Reservation> reservationOptional = reservationDAO.findById(id);

        if(reservationOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reservationDAO.deleteById(id);

        return new ResponseEntity<>(reservationOptional.get(),HttpStatus.OK);
    }
}