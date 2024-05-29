package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.repository.IDao;
import com.backend.clinicaOdontologica.service.IOdontologoService;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        return null;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        return null;
    }
}






  /*  @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        //return odontologoIDao.registrar(odontologoEntradaDto);
        return null;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
       // return odontologoIDao.listarTodos();
        return null;
  */


