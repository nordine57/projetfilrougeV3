package com.example.projetfilrouge.Model;

import com.example.projetfilrouge.view.UtilisateurView;
import com.example.projetfilrouge.view.VoitureView;
import com.fasterxml.jackson.annotation.JsonView;
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
    protected Long id;
    @JsonView({VoitureView.class, UtilisateurView.class})
    @Column( length = 100)
    public String plaqueVoiture;

    @JsonView({VoitureView.class})
    @ManyToOne(optional = true)
    protected Utilisateur idutilisateur;

    @JsonView({VoitureView.class, UtilisateurView.class})
    @ManyToOne(optional = true)
    protected ModeleVoiture idModeleVoiture;


}
