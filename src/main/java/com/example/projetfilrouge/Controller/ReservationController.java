package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.ReservationDAO;
import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Garage;
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
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    ReservationDAO reservationDAO;

    @Autowired
    GarageDAO garageDAO;

    @Autowired
    UtilisateurDao utilisateurDao;


    @GetMapping("/reservation/liste")
    @JsonView(ReservationView.class)
    public List<Reservation> liste() {
        return reservationDAO.findAll();
    }

    @JsonView(ReservationView.class)
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
    public ResponseEntity<Map<String, Object>> addVoiture(@RequestBody Reservation reservation, @AuthenticationPrincipal AppUserDetails user) {

        reservation.setReservation(user.getUtilisateur());
        Garage garage = new Garage(); // par exemple, en utilisant l'identifiant du garage
        // Rechercher le garage dans la base de données pour s'assurer qu'il existe
        Garage GarageExist = garageDAO.findById(garage.getIdGarage()).orElse(null);

        if (GarageExist == null) {
            // Gérer le cas où le garage n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le garage n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        // Assigner le garage récupéré à l'utilisateur
        reservation.setIdgarage(GarageExist);


        reservationDAO.save(reservation);
        return new ResponseEntity<>(Map.of("message","Reservation creer"), HttpStatus.CREATED);
    }

    @JsonView(ReservationView.class)
    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> update (@PathVariable Long id,@AuthenticationPrincipal AppUserDetails user) {


        Optional<Reservation> reservationOptional = reservationDAO.findById(id);

        //l'utilisateur tente de modifier un utilisateur qui n'existe pas/plus
        if(reservationOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reservationOptional.get().setExecutantreservation(user.getUtilisateur());
        reservationDAO.save(reservationOptional.get());
        return new ResponseEntity<>(reservationOptional.get(), HttpStatus.OK);
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