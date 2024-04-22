package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "_VoitureOccasion")
public class VoitureOccasion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idVoitureOccasion;
    @Column(nullable = false, length = 100)
    protected String nomVoitureOccasion;
    @Column(nullable = false, length = 100)
    protected String prixVoitureOccasion;
    @Column(nullable = false, length = 100)
    protected Date DateArriver;
    @Column(nullable = false, length = 100)
    protected String plaqueVoitureOccasion;



}

