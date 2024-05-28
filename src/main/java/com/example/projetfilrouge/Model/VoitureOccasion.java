package com.example.projetfilrouge.Model;

import com.example.projetfilrouge.view.ReservationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "_VoitureOccasion")
public class VoitureOccasion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = true, length = 100)
    protected String nomVoitureOccasion;
    @Column(nullable = true, length = 100)
    protected int prixVoitureOccasion;
    @Column(nullable = true, length = 100)
    @CreationTimestamp
    protected LocalDate dateArriver;
    @Column(nullable = true ,columnDefinition = "TEXT")
    protected String description;
    @Column(nullable = true ,columnDefinition = "TEXT")
    private String image;

    @ManyToOne(optional = true)
    protected Garage garage ;


}

