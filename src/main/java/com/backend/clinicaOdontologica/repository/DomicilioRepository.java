package com.backend.clinicaOdontologica.repository;

import com.backend.clinicaOdontologica.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}