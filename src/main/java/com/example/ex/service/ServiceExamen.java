package com.example.ex.service;

import com.example.ex.entity.*;

import java.util.Date;
import java.util.List;

public interface ServiceExamen {
    Clinique addClinique(Clinique clinique);
    Medecin addMedecinAndAssignToClinique(Medecin medecin,Long cliniqueId);
    Patient addPatient(Patient patient);
    RendezVous addRDVAndAssignMedAndPatient(RendezVous rendezVous,Long idMedecin,Long idPatient );
    List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long cliniqueId, Specialite specialite);
    int getNbrRendezVousMedecin(Long idMedecin);
    int getRevenueMedecin(Long idMedecin, Date startDate, Date endDate);


}
