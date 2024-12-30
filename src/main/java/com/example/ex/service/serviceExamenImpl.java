package com.example.ex.service;

import com.example.ex.entity.*;
import com.example.ex.repository.CliniqueRepository;
import com.example.ex.repository.MedecinRepository;
import com.example.ex.repository.PatientRepository;
import com.example.ex.repository.RendezVousRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class serviceExamenImpl implements ServiceExamen{
    @Autowired
    private CliniqueRepository CliniqueRepository;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private CliniqueRepository cliniqueRepository;

    @Override
    public Clinique addClinique(Clinique clinique) {
        return cliniqueRepository.save(clinique);
    }


    @Override
    public Medecin addMedecinAndAssignToClinique(Medecin medecin, Long cliniqueId) {
        Clinique c=cliniqueRepository.findById(cliniqueId).orElse(null);
        List<Medecin> list=new ArrayList<>();
        list.add(medecin);
        if (c.getMedecins()==null) {
            c.setMedecins(list);
        }
            else{
                c.getMedecins().add(medecin);
            }

        return medecinRepository.save(medecin) ;
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public RendezVous addRDVAndAssignMedAndPatient(RendezVous rendezVous, Long idMedecin, Long idPatient) {
        Medecin m=medecinRepository.findById(idMedecin).orElse(null);
        Patient p=patientRepository.findById(idPatient).orElse(null);
        rendezVous.setPatient(p);
        rendezVous.setMedecin(m);

        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long cliniqueId, Specialite specialite) {
        Clinique c=cliniqueRepository.findById(cliniqueId).orElse(null);
        List<RendezVous> list=rendezVousRepository.findAll();
        List<RendezVous> reusultat=new ArrayList<>();
        for (RendezVous r:list) {
            Medecin m=r.getMedecin();
            if (m.getCliniques()!=null) {
                for (Clinique clinique:m.getCliniques()){
                    if (c.equals(clinique)&& m.getSpecialite().equals(specialite)) {
                        reusultat.add(r);
                    }
                }
            }

        }

        return reusultat;
    }

    @Override
    public int getNbrRendezVousMedecin(Long idMedecin) {

        //return rendezVousRepository.countByMedecin_IdMedecin(idMedecin);
        int nb=0;
        Medecin m=medecinRepository.findById(idMedecin).orElse(null);
        List<RendezVous> list=rendezVousRepository.findAll();
        for (RendezVous r:list) {
            if (r.getMedecin().equals(m)) {
                nb++;
            }
        }
        return nb;
    }

    @Override
    public int getRevenueMedecin(Long idMedecin, Date startDate, Date endDate) {
        int nb=0;
        Medecin m=medecinRepository.findById(idMedecin).orElse(null);
        List<RendezVous> list=rendezVousRepository.findAll();
        for (RendezVous r:list) {
            if (r.getMedecin().equals(m) && r.getDateRDV().after(startDate) && r.getDateRDV().before(endDate)) {
                nb++;
            }
        }
        return m.getPrixConsultation()*nb;
    }




    @Scheduled(cron = "*/30 * * * * *")
    public void retriveRendezVous(){
        List<RendezVous> list=rendezVousRepository.findAll();
        for (RendezVous r:list){
            if (r.getDateRDV().after(new Date())) {
                log.info("la liste des rendezvous"+r.getDateRDV()+" : Medecin :"+r.getMedecin().getNomMedecin()+" : patient: "+r.getPatient().getNomPatient());
            }
        }
    }
}

