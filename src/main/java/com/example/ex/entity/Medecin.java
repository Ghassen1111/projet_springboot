package com.example.ex.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedecin;

    private String nomMedecin;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    private int telephone;
    private int prixConsultation;

    @ManyToMany(mappedBy = "medecins")
    private List<Clinique> cliniques;

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVousList;
}
