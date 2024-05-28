package com.example.projetfilrouge.Model;

import com.example.projetfilrouge.view.BoxView;
import com.example.projetfilrouge.view.ReservationView;
import com.example.projetfilrouge.view.TacheView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Box")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ReservationView.class, BoxView.class})
    protected Long idBox;

    @Column(nullable = false, length = 100)
    @JsonView({ReservationView.class, BoxView.class})
    protected String nomBox;

    @OneToMany(mappedBy = "box")
    private List<Reservation> reservations;

}
