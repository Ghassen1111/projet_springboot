package com.example.ex.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor//this.
@NoArgsConstructor//constructeur par défaut
@ToString
@Entity
public class Clinique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClinique;

    private String nomClinique;
    private String adresse;
    private int telephone;

    @ManyToMany
    private List<Medecin> medecins;
}
