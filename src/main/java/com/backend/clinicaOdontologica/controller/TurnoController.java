package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDtoId;
import com.backend.clinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private final ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@RequestBody @Valid TurnoEntradaDtoId turnoEntradaDtoId) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDtoId), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }


    @GetMapping("/{id}")//localhost:8080/turnos/x
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    //PUT
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto, @PathVariable Long id) {
        return new ResponseEntity<>(turnoService.actualizarTurno(turnoEntradaDto, id), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/eliminar")//localhost:8080/turnos/eliminar?id=x
    public ResponseEntity<?> eliminarTurno(@RequestParam Long id) {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}