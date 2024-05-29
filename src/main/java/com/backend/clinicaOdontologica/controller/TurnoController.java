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
