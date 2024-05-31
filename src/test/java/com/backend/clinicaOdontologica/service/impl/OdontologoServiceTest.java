package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

   @Test
    void deberiaRetornarseUnaListaNoVaciaDeOdontologosEnH2() {
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

    @Test
    void deberiaRegistrarseUnOdontologoYObtenerElIdCorrespondiente() {

        OdontologoEntradaDto odontologo = new OdontologoEntradaDto( 12345, "Juan", "Perez");

        OdontologoSalidaDto odontolgoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontolgoRegistrado.getId());

    }

}
