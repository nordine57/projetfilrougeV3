package com.example.projetfilrouge.Model;

import com.example.projetfilrouge.view.ReservationView;
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
    @JsonView({UtilisateurView.class})
    protected Integer idUser;

    @JsonView({UtilisateurView.class, ReservationView.class})
    protected String nom;

    @JsonView({UtilisateurView.class, ReservationView.class})
    protected String prenom;

    @JsonView({UtilisateurView.class})
    @Column( unique = true)
    protected String email;

    protected String motDePasse;

    //@JsonView(UtilisateurView.class)
    //protected boolean administrateur;

    @ManyToOne
    protected Role role;

    @ManyToOne(optional = true)
    protected Garage garage;

    @JsonView({UtilisateurView.class})
    @OneToMany(mappedBy = "idutilisateur")
    private List<Voiture> voiture;


    @OneToMany(mappedBy = "reservation")
    private List<Reservation> reservations;

    @JsonView(ReservationView.class)
    @OneToMany(mappedBy = "executantreservation")
    private List<Reservation> executantreservation;

}

