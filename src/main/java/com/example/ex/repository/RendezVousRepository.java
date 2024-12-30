package com.example.ex.repository;

import com.example.ex.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    int countByMedecin_IdMedecin(long idMedecin);
    @Query("SELECT COUNT (r) FROM RendezVous r WHERE r.medecin.idMedecin=:idMedecin")
    int countByMedecin(@Param("idMedecin") long idMedecin);
}
