package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaOdontologica.service.ITurnoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class TurnoController {


    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @PostMapping("/registrar")
    public TurnoSalidaDto registrarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto){
        return turnoService.registrarTurno(turnoEntradaDto);
    }

     public List<TurnoSalidaDto> listarTurnos(){
        return turnoService.listarTurnos();
     }
}
