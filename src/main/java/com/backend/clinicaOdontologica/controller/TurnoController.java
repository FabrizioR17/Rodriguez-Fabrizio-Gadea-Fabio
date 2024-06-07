package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaOdontologica.service.ITurnoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public TurnoSalidaDto registrarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto){
        return turnoService.registrarTurno(turnoEntradaDto);
    }

    @GetMapping("/listar")
    public List<TurnoSalidaDto> listarTurnos(){
        return turnoService.listarTurnos();
     }
}


//    @PostMapping("/registrar")
//    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
//        return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteEntradaDto), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/listar")
//    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes(){
//        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
//    }
//
//
//    @GetMapping("/{id}")//localhost:8080/pacientes/x
//    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id){
//        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
//    }
//
//    //PUT
//    @PutMapping("/actualizar/{id}")
//    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto, @PathVariable Long id){
//        return new ResponseEntity<>(pacienteService.actualizarPaciente(pacienteEntradaDto, id), HttpStatus.OK);
//    }
//
//    //DELETE
//    @DeleteMapping("/eliminar")//localhost:8080/pacientes/eliminar?id=x
//    public ResponseEntity<?> eliminarPaciente(@RequestParam Long id){
//        pacienteService.eliminarPaciente(id);
//        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
//    }
