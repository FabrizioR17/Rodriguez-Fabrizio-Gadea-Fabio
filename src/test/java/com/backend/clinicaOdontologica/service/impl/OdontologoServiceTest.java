package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.OdontologoSalidaDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

   @Test
    public void deberiaRetornarseUnaListaNoVaciaDeOdontologosEnH2() {
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

    @Test
    public void deberiaRegistrarseUnOdontologoYObtenerElIdCorrespondiente() {

        OdontologoEntradaDto odontologo = new OdontologoEntradaDto( 12345, "Juan", "Perez");

        OdontologoSalidaDto odontolgoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontolgoRegistrado.getId());

    }

}
