package com.backend.clinicaOdontologica.test;

import com.backend.clinicaOdontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaOdontologica.entity.Domicilio;
import com.backend.clinicaOdontologica.entity.Paciente;
import com.backend.clinicaOdontologica.repository.impl.PacienteDaoH2;
import com.backend.clinicaOdontologica.service.impl.PacienteService;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class PacienteServiceTest {

    private PacienteService pacienteService;

 /*   @Test
    public void deberiaRegistrarseUnPacienteYObtenerElIdCorrespondienteParaPacienteYDomicilioEnH2(){

        pacienteService = new PacienteService(new PacienteDaoH2());
        PacienteEntradaDto paciente = new PacienteEntradaDto("Nombre", "Apellido", 7891011, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 13, "Localidad", "Provincia"));

        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        assertNotNull(pacienteRegistrado.getDomicilio().getId());
        assertNotNull(pacienteRegistrado.getId());

    }

    @Test
    public void deberiaRetornarseUnaListaNoVaciaDePacientesEnH2(){
        pacienteService = new PacienteService(new PacienteDaoH2());
        assertFalse(pacienteService.listarPacientes().isEmpty());
    }
*/



}