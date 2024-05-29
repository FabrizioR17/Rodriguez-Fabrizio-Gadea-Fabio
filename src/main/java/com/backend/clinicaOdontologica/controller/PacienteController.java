package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaOdontologica.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public PacienteSalidaDto registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
        return pacienteService.registrarPaciente(pacienteEntradaDto);
    }

    @GetMapping("/listar")
    public List<PacienteSalidaDto> listarPacientes(){
        return pacienteService.listarPacientes();
    }
}
