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
    protected Long id;
    @Column(nullable = false, length = 100)
    protected String nomVoitureOccasion;
    @Column(nullable = false, length = 100)
    protected int prixVoitureOccasion;
    @Column(nullable = false, length = 100)
    protected Date dateArriver;
    @Column(columnDefinition = "TEXT")
    protected String description;
    @Column(columnDefinition = "TEXT")
    private String image;



}

