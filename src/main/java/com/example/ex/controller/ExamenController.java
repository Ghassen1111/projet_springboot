package com.example.ex.controller;

import com.example.ex.entity.*;
import com.example.ex.service.ServiceExamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//beb dar kbiir
@RequestMapping("/ex")

public class ExamenController {
    //injection des service dima tsire 3la interface mech 3la classe
    @Autowired
    private ServiceExamen serviceExamen;
    @PostMapping("/addClinique")
    public Clinique addClinique(@RequestBody Clinique clinique) {
        return serviceExamen.addClinique(clinique);
    }
    @PostMapping("/addPatient")
    public Patient addPatient(@RequestBody Patient patient) {
        return serviceExamen.addPatient(patient);
    }
    @PostMapping("/addPatient/{idClinique}")
    public Medecin addMedecinAndAssignToClinique(@RequestBody Medecin medecin, @PathVariable Long idClinique) {
        return serviceExamen.addMedecinAndAssignToClinique(medecin,idClinique);
    }
    @PostMapping("/addRDVAndAssignMedAndPatient/{idP}/{idM}")
    public RendezVous addRDVAndAssignMedAndPatient(@RequestBody RendezVous rendezVous, @PathVariable Long idP, @PathVariable Long idM) {
        return serviceExamen.addRDVAndAssignMedAndPatient(rendezVous,idP,idM);

    }

    @GetMapping("/getRendezVousByCliniqueAndSpecialite/{idC}/{sp}")
    public List<RendezVous>getRDV(@PathVariable Long idC, Specialite sp){
        return serviceExamen.getRendezVousByCliniqueAndSpecialite(idC,sp);
    }
    @GetMapping("/getNbrRendezVousMedecin/{idM}")
    public int getNbrRendezVousMedecin(@PathVariable Long idM){
        return serviceExamen.getNbrRendezVousMedecin(idM);
    }
    @GetMapping("/getRevenue/{idM}/{startDate}/{endDate}")
    public int getRevenue(@PathVariable Long idM
                          ,@PathVariable @DateTimeFormat (pattern = "yyyy-MM-dd") Date startDate
                          ,@PathVariable @DateTimeFormat (pattern = "yyyy-MM-dd") Date endDate){
       return serviceExamen.getRevenueMedecin(idM,startDate,endDate);
    }



}
