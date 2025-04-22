package com.example.projetfilrouge.Model;

import com.example.projetfilrouge.view.ReservationView;
import com.example.projetfilrouge.view.UtilisateurView;
import com.example.projetfilrouge.view.VoitureView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ReservationView.class,  UtilisateurView.class})
    protected Long idReservation;

    @Column(nullable = false, length = 100)
    @JsonView({ReservationView.class, UtilisateurView.class})
    protected String nomReservation;

    @Column(nullable = false, length = 100)
    @JsonView({ReservationView.class, UtilisateurView.class})
    protected LocalDate dateReservation;

    @Column(nullable = false, length = 100)
    @JsonView({ReservationView.class, UtilisateurView.class})
    protected LocalTime heureReservation;

    @Column( length = 100)
    @JsonView(ReservationView.class)
    public String problemeVoiture;

    @Column(nullable = true, length = 100)
    @JsonView(ReservationView.class)
    protected LocalTime heureFin;


    @ManyToOne(optional = true)
    @JsonView(ReservationView.class)
    protected Utilisateur reservation ;

    @ManyToOne(optional = true)
    @JsonView(ReservationView.class)
    protected Utilisateur executantreservation ;

    @ManyToOne(optional = true)
    @JsonView(ReservationView.class)
    protected Tache tache ;

    @ManyToOne(optional = true)
    @JsonView(ReservationView.class)
    protected Box box ;

    @JsonView(ReservationView.class)
    @ManyToOne(optional = true)
    protected Garage idgarage ;



}



