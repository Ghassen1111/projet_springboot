package com.example.ex.repository;

import com.example.ex.entity.Clinique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//pour injecté cette repository dans notre service
@Repository
public interface CliniqueRepository extends JpaRepository<Clinique, Long> {
}
