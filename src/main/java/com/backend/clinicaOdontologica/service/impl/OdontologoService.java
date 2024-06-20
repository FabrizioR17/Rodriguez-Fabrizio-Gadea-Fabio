package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.repository.OdontogoloRepository;
import com.backend.clinicaOdontologica.service.IOdontologoService;
import com.backend.clinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final OdontogoloRepository odontogoloRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontogoloRepository odontogoloRepository, ModelMapper modelMapper) {
        this.odontogoloRepository = odontogoloRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {

       OdontologoSalidaDto odontologoSalidaDto;
       LOGGER.info("OdontologoEntradaDTO: {}", JsonPrinter.toString(odontologoEntradaDto));
       Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
       LOGGER.info("OdontologoMapeado: {} ", JsonPrinter.toString(odontologo));
       odontologoSalidaDto = modelMapper.map(odontogoloRepository.save(odontologo), OdontologoSalidaDto.class);
       LOGGER.info("OdotologoSalidaDTO: {}", JsonPrinter.toString(odontologo));
       return odontologoSalidaDto;

    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontogoloRepository.findAll().stream().map(odontolgo -> modelMapper.map(odontolgo, OdontologoSalidaDto.class)).toList();
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(odontologos));
        return odontologos;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {

        Odontologo odontologoBuscado = odontogoloRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;
        if (odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado {}", JsonPrinter.toString(odontologoEncontrado));

        }else LOGGER.error("Odontologo no se ha encontrado con el id: {}", id);

        return odontologoEncontrado;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontogoloRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontolog con id {}", id);
        }else{
            throw new ResourceNotFoundException("No existe registro de odontolgo en base de datos con el id que buscas para eliminar: " + id);
        }
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) throws BadRequestException {

        Odontologo odontologoRecibido = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        Odontologo odontologoAModificar = odontogoloRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;
        if (odontologoAModificar != null){
            odontologoRecibido.setId(odontologoAModificar.getId());
            odontologoAModificar = odontologoRecibido;

            odontogoloRepository.save(odontologoAModificar);
            odontologoSalidaDto = modelMapper.map(odontologoAModificar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo modificado: {}", JsonPrinter.toString(odontologoSalidaDto));
        }else {
            LOGGER.error("No fue posible modificar el odontologo porque no se encuentra en la base de datos.");
            throw new BadRequestException("No fue posible modificar el odontologo porque no se encuentra en la base de datos" + odontologoEntradaDto);
        }
        return odontologoSalidaDto;
    }


}


