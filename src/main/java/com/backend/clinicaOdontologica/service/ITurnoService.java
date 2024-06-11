package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDtoId;
import com.backend.clinicaOdontologica.dto.salida.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDtoId turnoEntradaDtoId);
    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);
    void eliminarTurno(Long id);
    TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id);

}
