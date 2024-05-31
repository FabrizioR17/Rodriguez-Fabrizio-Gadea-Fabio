package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaOdontologica.entity.Paciente;
import com.backend.clinicaOdontologica.repository.IDao;
import com.backend.clinicaOdontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IDao<Paciente> pacienteIDao;
    private final ModelMapper modelMapper;


    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

  // @Override
   // public Paciente registrarPaciente(Paciente paciente) { return pacienteIDao.registrar(paciente); }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        LOGGER.info("PacienteEntradaDTO: " +
                pacienteEntradaDto);
        Paciente paciente = modelMapper.map(pacienteEntradaDto, Paciente.class);
        LOGGER.info("PacienteEntidad: " + paciente);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteIDao.registrar(paciente), PacienteSalidaDto.class);
        LOGGER.info("PacienteSalidaDTO: " + pacienteSalidaDto);

        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<PacienteSalidaDto> pacientes = pacienteIDao.listarTodos().stream().map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class)).toList();
        LOGGER.info("Listado de todos los pacientes: {}", pacientes);
        return pacientes;
    }

    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class).addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class).addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }
}
