package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDtoId;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    private static PacienteSalidaDto pacienteSalidaDto;
    private static OdontologoSalidaDto odontologoSalidaDto;

    @BeforeAll
    static void setup(@Autowired PacienteService pacienteService, @Autowired OdontologoService odontologoService) {
        // Registrar un paciente
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Carolina", "Flores", 72395578, LocalDate.of(2024, 12, 15),
                new DomicilioEntradaDto("Buendia", 282, "Lima", "Lima"));
        pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        // Registrar un odontólogo
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(1001, "Juan", "Perez");
        odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
    }

    @Test
    @Order(1)
    void deberiaRegistrarseUnTurno_yRetornarSuId() throws BadRequestException {
        TurnoEntradaDtoId turnoEntradaDtoId = new TurnoEntradaDtoId(pacienteSalidaDto.getId(), odontologoSalidaDto.getId(), LocalDateTime.of(2024, 8, 10, 10, 0));
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDtoId);

        // assert
        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());
        assertEquals(pacienteSalidaDto.getId(), turnoSalidaDto.getPacienteSalidaDto().getId());
        assertEquals(odontologoSalidaDto.getId(), turnoSalidaDto.getOdontologoSalidaDto().getId());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeTurnos() {
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElTurnoConId1() {
        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeTurnos() {
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertTrue(listadoDeTurnos.isEmpty());
    }
}
