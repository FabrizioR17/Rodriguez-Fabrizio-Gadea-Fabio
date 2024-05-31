package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.repository.IDao;
import com.backend.clinicaOdontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IDao<Odontologo> odontologoIDao;
    private final ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
       LOGGER.info("OdontologoEntradaDTO: " + odontologoEntradaDto);
       Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
       LOGGER.info("OdontologoEntidad: " + odontologo);
       OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoIDao.registrar(odontologo), OdontologoSalidaDto.class);
       LOGGER.info("OdotologoSalidaDTO: " + odontologoSalidaDto);
        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoIDao.listarTodos().stream().map(odontolgo -> modelMapper.map(odontolgo, OdontologoSalidaDto.class)).toList();
        return odontologos;
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


