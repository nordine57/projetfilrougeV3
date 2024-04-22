package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "outilgarage")
public class OutilGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idOutilGarage;
    @Column(nullable = false, length = 100)
    protected String nomOutilGarage;

    

}
