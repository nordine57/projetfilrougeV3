package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "_Marque")
public class MarqueVoiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idMarque;
    @Column(nullable = false, length = 100)
    protected String nomMarque;

}

