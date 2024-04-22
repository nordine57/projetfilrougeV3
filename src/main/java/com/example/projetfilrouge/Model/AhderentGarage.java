package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ahderentgarage")
public class AhderentGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer ida;
    @Column(nullable = false, length = 100)
    protected Date dateInscription;





}