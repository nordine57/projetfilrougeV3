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
    @Column(nullable = false, length = 100)
    protected String nom;
    @Column(nullable = false, length = 100)
    protected String prenom;
    @Column(nullable = false, length = 100)
    protected String email;
    @Column(nullable = false, length = 100)
    protected String motDePasse;

    @JsonView(UtilisateurView.class)
    protected boolean administrateur;

    @ManyToOne(optional = false)
    protected Role role;



}

