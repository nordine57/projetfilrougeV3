package com.example.projetfilrouge.Model;

import com.example.projetfilrouge.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idUser;

    protected String nom;

    protected String prenom;

    @JsonView({UtilisateurView.class})
    @Column( unique = true)
    protected String email;

    protected String motDePasse;

    //@JsonView(UtilisateurView.class)
    //protected boolean administrateur;

    @ManyToOne
    protected Role role;



}

