//package com.backend.clinicaOdontologica.service.impl;
//
//import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
//import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.Assert.*;
//
//@SpringBootTest
//public class OdontologoServiceTest {
//
//    @Autowired
//    private OdontologoService odontologoService;
//
//   @Test
//    void deberiaRetornarseUnaListaNoVaciaDeOdontologosEnH2() {
//        assertFalse(odontologoService.listarOdontologos().isEmpty());
//    }
//
//    @Test
//    void deberiaRegistrarseUnOdontologoYObtenerElIdCorrespondiente() {
//
//        OdontologoEntradaDto odontologo = new OdontologoEntradaDto( 12345, "Juan", "Perez");
//
//        OdontologoSalidaDto odontolgoRegistrado = odontologoService.registrarOdontologo(odontologo);
//
//        assertNotNull(odontolgoRegistrado.getId());
//
//    }
//
//}
package com.backend.clinicaOdontologica.service.impl;
import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarseUnOdontologoDeNombreJuan_yRetornarSuId() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(1001, "Juan", "Perez");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        // assert
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Juan", odontologoSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeOdontologos() {
        List<OdontologoSalidaDto> listadoDeOdontologos = odontologoService.listarOdontologos();
        assertFalse(listadoDeOdontologos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElOdontologoConId1() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeOdontologos() {
        List<OdontologoSalidaDto> listadoDeOdontologos = odontologoService.listarOdontologos();
        assertTrue(listadoDeOdontologos.isEmpty());
    }
}
