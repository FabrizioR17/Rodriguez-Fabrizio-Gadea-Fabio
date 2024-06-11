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
import com.backend.clinicaOdontologica.repository.PacienteRepository;
import com.backend.clinicaOdontologica.repository.OdontogoloRepository;
import com.backend.clinicaOdontologica.repository.TurnoRepository;
import com.backend.clinicaOdontologica.service.ITurnoService;
import com.backend.clinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



import java.util.List;

//@Service
//public class TurnoService implements ITurnoService {
//    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
//    private final TurnoRepository turnoRepository;
//    private final ModelMapper modelMapper;
//
//    public TurnoService( TurnoRepository turnoRepository, ModelMapper modelMapper) {
//        this.turnoRepository = turnoRepository;
//        this.modelMapper = modelMapper;
//        configureMapping();
//    }
//
//
//    @Override
//    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
//       // return turnoIDao.registrar(turnoEntradaDto);
//        LOGGER.info("Datos de TurnoEntradaDto: " + turnoEntradaDto);
//        Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
//        LOGGER.info("TurnoEntidad: "+ turno);
//        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDto.class);
//        LOGGER.info("TurnoSalidaDto: "+ turnoSalidaDto);
//    return turnoSalidaDto;
//    }
//
//    @Override
//    public List<TurnoSalidaDto> listarTurnos() {
//        //return turnoIDao.listarTodos();
//        List<TurnoSalidaDto> turnos = turnoRepository.findAll().stream().map(turno -> modelMapper.map(turno, TurnoSalidaDto.class)).toList();
//        LOGGER.info("Listado de todos los turnos: " + turnos);
//   return turnos;
//    }
//
//    @Override
//    public TurnoSalidaDto buscarTurnoPorId(Long id) {
//        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
//        TurnoSalidaDto turnoEncontrado = null;
//
//        if (turnoBuscado != null){
//            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
//            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
//        } else LOGGER.error("No se ha encontrado el turno con id {}", id);
//
//        return turnoEncontrado;
//    }
//
//    @Override
//    public void eliminarTurno(Long id) {
//        if (buscarTurnoPorId(id) != null){
//            turnoRepository.deleteById(id);
//            LOGGER.warn("Se ha eliminado el turno con id {}", id);
//        }
//     }
//
//    @Override
//    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {
//        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
//        Turno turnoAModificar = turnoRepository.findById(id).orElse(null);
//        TurnoSalidaDto turnoSalidaDto = null;
//
//        if (turnoAModificar != null){
//            turnoRecibido.setId(turnoAModificar.getId());
//            turnoRecibido.getOdontologo().setId(turnoAModificar.getOdontologo().getId());
//            turnoRecibido.getPaciente().setId(turnoAModificar.getPaciente().getId());
//            turnoAModificar = turnoRecibido;
//
//            turnoRepository.save(turnoAModificar);
//            turnoSalidaDto = modelMapper.map(turnoAModificar, TurnoSalidaDto.class);
//            LOGGER.warn("Turno modificado: {}", JsonPrinter.toString(turnoSalidaDto));
//
//        } else {
//            LOGGER.error("No fue posible modificar el turno porque no se encuentra en la base de datos");}
//
//        return turnoSalidaDto;
//    }
//
//    private void configureMapping() {
//        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
//                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
//        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
//                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
//    }
//
//}

@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontogoloRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontogoloRepository odontologoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDtoId turnoEntradaDtoId) {
        LOGGER.info("Datos de TurnoEntradaDto: " + turnoEntradaDtoId);

        Long idPaciente = turnoEntradaDtoId.getIdPaciente();

        Long idOdontologo = turnoEntradaDtoId.getIdOdontologo();

        Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
        Odontologo odontologo = odontologoRepository.findById(idOdontologo).orElse(null);

        Turno turno = new Turno();
        turno.setFechaHora(turnoEntradaDtoId.getFechaHora());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        Turno savedTurno = turnoRepository.save(turno);



//        // Mapea las entidades
//        Paciente paciente = modelMapper.map(turnoEntradaDto.getPacienteEntradaDto(), Paciente.class);
//        Odontologo odontologo = modelMapper.map(turnoEntradaDto.getOdontologoEntradaDto(), Odontologo.class);
//
//        // Guarda o actualiza Paciente y Odontologo
//        paciente = pacienteRepository.save(paciente);
//        odontologo = odontologoRepository.save(odontologo);
//
//        // Crear y guardar el turno con las relaciones adecuadas
//        Turno turno = new Turno();
//        turno.setFechaHora(turnoEntradaDto.getFechaHora());
//        turno.setPaciente(paciente);
//        turno.setOdontologo(odontologo);
//
//        Turno savedTurno = turnoRepository.save(turno);
//
//        // Mapea la salida
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(savedTurno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteSalidaDto(modelMapper.map(paciente, PacienteSalidaDto.class));
        turnoSalidaDto.setOdontologoSalidaDto(modelMapper.map(odontologo, OdontologoSalidaDto.class));

        LOGGER.info("TurnoSalidaDto: " + turnoSalidaDto);
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
        LOGGER.info("Listado de todos los turnos: " + turnos);
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
    public void eliminarTurno(Long id) {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        }
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {
        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoAModificar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAModificar != null) {
            turnoRecibido.setId(turnoAModificar.getId());
            turnoRecibido.getOdontologo().setId(turnoAModificar.getOdontologo().getId());
            turnoRecibido.getPaciente().setId(turnoAModificar.getPaciente().getId());
            turnoAModificar = turnoRecibido;

            turnoRepository.save(turnoAModificar);
            turnoSalidaDto = modelMapper.map(turnoAModificar, TurnoSalidaDto.class);
            turnoSalidaDto.setPacienteSalidaDto(modelMapper.map(turnoAModificar.getPaciente(), PacienteSalidaDto.class));
            turnoSalidaDto.setOdontologoSalidaDto(modelMapper.map(turnoAModificar.getOdontologo(), OdontologoSalidaDto.class));

            LOGGER.warn("Turno modificado: {}", JsonPrinter.toString(turnoSalidaDto));
        } else {
            LOGGER.error("No fue posible modificar el turno porque no se encuentra en la base de datos");
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