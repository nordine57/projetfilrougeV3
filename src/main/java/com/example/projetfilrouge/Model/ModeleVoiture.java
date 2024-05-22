package com.example.projetfilrouge.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "_Modele")
public class ModeleVoiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idModele;
    @Column(nullable = false, length = 100)
    protected String nomModele;

    @ManyToOne
    protected MarqueVoiture marqueVoiture;

}
