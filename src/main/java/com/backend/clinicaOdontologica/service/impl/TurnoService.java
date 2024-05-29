package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaOdontologica.entity.Turno;
import com.backend.clinicaOdontologica.repository.IDao;
import com.backend.clinicaOdontologica.service.ITurnoService;

import java.util.List;

public class TurnoService implements ITurnoService {

private IDao<Turno> turnoIDao;

    public TurnoService(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
       // return turnoIDao.registrar(turnoEntradaDto);
    return null;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        //return turnoIDao.listarTodos();
   return null;
    }
}
