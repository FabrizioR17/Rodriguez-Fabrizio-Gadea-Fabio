package com.backend.clinicaOdontologica.test;

import com.backend.clinicaOdontologica.entity.Odontologo;

import com.backend.clinicaOdontologica.repository.impl.OdontologoDaoH2;
import com.backend.clinicaOdontologica.service.impl.OdontologoService;
import org.junit.Test;

import static org.junit.Assert.*;

public class OdontologoServiceTest {

    private OdontologoService odontologoService;
  /*  @Test
    public void deberiaRetornarseUnaListaNoVaciaDeOdontologosEnH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listarTodos().isEmpty());

    }

    @Test
    public void deberiaRegistrarseUnOdontologoYObtenerElIdCorrespondiente() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());

        Odontologo odontologo = new Odontologo(12345, "Juan", "Perez");

        Odontologo odontoloRegistrado = odontologoService.registrar(odontologo);

        assertNotNull(odontoloRegistrado.getId());
    }
*/
}
