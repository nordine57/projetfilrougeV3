package com.example.projetfilrouge.Model;

import com.example.projetfilrouge.view.ReservationView;
import com.example.projetfilrouge.view.TacheView;
import com.example.projetfilrouge.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tache")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(TacheView.class)
    protected Long idTache;

    @Column(nullable = false, length = 100)
    @JsonView({ReservationView.class, TacheView.class})
    protected String nomTache;

    @Column(nullable = false, length = 100)
    @JsonView({ReservationView.class, TacheView.class})
    protected Long dureeTache;

    @OneToMany(mappedBy = "tache")
    private List<Reservation> reservations;




}
