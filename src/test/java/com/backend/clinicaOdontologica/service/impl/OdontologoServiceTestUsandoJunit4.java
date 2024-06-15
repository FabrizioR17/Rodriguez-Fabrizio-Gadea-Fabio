//package com.backend.clinicaOdontologica.service.impl;
//
//import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
//import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//public class OdontologoServiceTestUsandoJunit4 {
//
//    @Autowired
//    OdontologoService odontologoService;
//
//    @Test
//    public void listarOdontologos2() {
//        assertFalse(odontologoService.listarOdontologos().isEmpty());
//
//    }
//
//    @Test
//    public void deberiablabla () {
//
//        OdontologoEntradaDto odontologo = new OdontologoEntradaDto(12345, "Juan", "Perez");
//        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);
//
//        assertNotNull(odontologoRegistrado.getId());
//
//    }
//
//}