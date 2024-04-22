package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idReservation;
    @Column(nullable = false, length = 100)
    protected String nomReservation;
    @Column(nullable = false, length = 100)
    protected Date dateReservation;
    @Column(nullable = false, length = 100)
    protected String heureReservation;
    @Column(nullable = false, length = 100)
    protected Boolean validationReservation;

}



