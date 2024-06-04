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
@Table(name = "_Modele")
public class ModeleVoiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idModele;
    @JsonView({VoitureView.class, UtilisateurView.class})
    @Column(nullable = false, length = 100)
    protected String nomModele;

    @JsonView({VoitureView.class, UtilisateurView.class})
    @ManyToOne
    protected MarqueVoiture marqueVoiture;

}
