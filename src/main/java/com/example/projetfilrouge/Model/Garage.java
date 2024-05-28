package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idGarage;
    @Column(nullable = false, length = 100)
    protected String nomGarage;
    @Column(nullable = false, length = 100)
    protected String ville;

    public Garage() {
        this.idGarage = 1;
    }

    @OneToMany(mappedBy = "idgarage")
    private List<Reservation> reservations;
}



