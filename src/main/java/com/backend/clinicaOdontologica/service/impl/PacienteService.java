package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaOdontologica.entity.Paciente;
import com.backend.clinicaOdontologica.repository.IDao;
import com.backend.clinicaOdontologica.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;


    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

  // @Override
   // public Paciente registrarPaciente(Paciente paciente) { return pacienteIDao.registrar(paciente); }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        return null;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        return null;
    }
}
