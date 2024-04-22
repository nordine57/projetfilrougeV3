package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "voiture")
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idVoiture;
    @Column(nullable = false, length = 100)
    protected String PlaqueVoiture;
    @Column(nullable = false, length = 100)
    protected String ProblemeVoiture;


}
