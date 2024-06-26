package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDtoId;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.entity.Paciente;
import com.backend.clinicaOdontologica.entity.Turno;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.repository.TurnoRepository;
import com.backend.clinicaOdontologica.service.ITurnoService;
import com.backend.clinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDtoId turnoEntradaDtoId) throws BadRequestException {

        LOGGER.info("Datos de TurnoEntradaDto: " + turnoEntradaDtoId);
        TurnoSalidaDto turnoSalidaDto;
        Long idPaciente = turnoEntradaDtoId.getIdPaciente();
        Long idOdontologo = turnoEntradaDtoId.getIdOdontologo();


        PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(idPaciente);
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(idOdontologo);

        if (pacienteSalidaDto != null && odontologoSalidaDto != null) {

            Paciente paciente = modelMapper.map(pacienteSalidaDto, Paciente.class);
            Odontologo odontologo = modelMapper.map(odontologoSalidaDto, Odontologo.class);

            Turno turno = new Turno();
            turno.setFechaHora(turnoEntradaDtoId.getFechaHora());
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);

            turnoSalidaDto = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDto.class);
            turnoSalidaDto.setPacienteSalidaDto(modelMapper.map(paciente, PacienteSalidaDto.class));
            turnoSalidaDto.setOdontologoSalidaDto(modelMapper.map(odontologo, OdontologoSalidaDto.class));

        }else if (pacienteSalidaDto == null && odontologoSalidaDto == null){
            throw new BadRequestException("El Paciente y Odontologo buscados para registrar turno, no existen en la base de datos. id Paciente: " + idPaciente + idOdontologo);
        }else if(odontologoSalidaDto == null){
            throw new BadRequestException("El Odontologo buscado para registrar turno, no se encuentra en la base de datos. id: " + idOdontologo);
        }else{
            throw new BadRequestException("El Pacientebuscado para registrar turno, no se encuentra en la base de datos. id: " + idPaciente);
        }


        LOGGER.info("TurnoSalidaDto: {}", JsonPrinter.toString(turnoSalidaDto));

        return turnoSalidaDto;

    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll().stream()
                .map(turno -> {
                    TurnoSalidaDto dto = modelMapper.map(turno, TurnoSalidaDto.class);
                    dto.setPacienteSalidaDto(modelMapper.map(turno.getPaciente(), PacienteSalidaDto.class));
                    dto.setOdontologoSalidaDto(modelMapper.map(turno.getOdontologo(), OdontologoSalidaDto.class));
                    return dto;
                })
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            turnoEncontrado.setPacienteSalidaDto(modelMapper.map(turnoBuscado.getPaciente(), PacienteSalidaDto.class));
            turnoEncontrado.setOdontologoSalidaDto(modelMapper.map(turnoBuscado.getOdontologo(), OdontologoSalidaDto.class));
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
        }

        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        }else{
            throw new ResourceNotFoundException("No existe registro de paciente en base de datos con el id que buscas: " + id);
        }
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws BadRequestException {
        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoAModificar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAModificar != null) {
            turnoRecibido.setId(turnoAModificar.getId());
            turnoRecibido.getOdontologo().setId(turnoEntradaDto.getOdontologoEntradaDto().getId());
            turnoRecibido.getPaciente().setId(turnoAModificar.getPaciente().getId());
            turnoRecibido.getPaciente().getDomicilio().setId(turnoAModificar.getPaciente().getDomicilio().getId());
            turnoAModificar = turnoRecibido;
            turnoRepository.save(turnoAModificar);
            turnoSalidaDto = modelMapper.map(turnoAModificar, TurnoSalidaDto.class);
            turnoSalidaDto.setPacienteSalidaDto(modelMapper.map(turnoAModificar.getPaciente(), PacienteSalidaDto.class));
            turnoSalidaDto.setOdontologoSalidaDto(modelMapper.map(turnoAModificar.getOdontologo(), OdontologoSalidaDto.class));

            LOGGER.warn("Turno modificado: {}", JsonPrinter.toString(turnoSalidaDto));
        } else {
            LOGGER.error("No fue posible modificar el turno porque no se encuentra en la base de datos");
            throw new BadRequestException("No fue posible modificar el turno porque no se encuentra en la base de datos" + turnoAModificar);
        }

        return turnoSalidaDto;
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }
}