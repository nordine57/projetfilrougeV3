package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idGarage;
    @Column(nullable = false, length = 100)
    protected String nomGarage;
    @Column(nullable = false, length = 100)
    protected String ville;
    @Column(nullable = false, length = 100)
    protected Integer numgarage;
    @Column(nullable = false, length = 100)
    protected String emplacement;

}



