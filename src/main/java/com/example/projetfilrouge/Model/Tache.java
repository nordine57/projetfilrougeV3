package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "tache")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idTache;
    @Column(nullable = false, length = 100)
    protected String nomTache;
    @Column(nullable = false, length = 100)
    protected String dureeTache;




}
