package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.service.IOdontologoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public OdontologoSalidaDto registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto){
        return odontologoService.registrarOdontologo(odontologoEntradaDto);
    }
    @GetMapping("/listar")
    public List<OdontologoSalidaDto> listarOdontologos(){return odontologoService.listarOdontologos();}
}
