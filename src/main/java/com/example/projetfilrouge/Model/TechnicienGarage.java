package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "technicienGarage")
public class TechnicienGarage  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idt;
    @Column(nullable = false, length = 100)
    protected String metier;
    @Column(nullable = false, length = 100)
    protected String equipe;




}