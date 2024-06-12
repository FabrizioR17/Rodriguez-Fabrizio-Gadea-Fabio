package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);
    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(Long id);
    void eliminarPaciente(Long id);
    PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);


}
