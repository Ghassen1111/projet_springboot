package com.example.ex.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRDV;

    private Date dateRDV;
    private String remarque;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
