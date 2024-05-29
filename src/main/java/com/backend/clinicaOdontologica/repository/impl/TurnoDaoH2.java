package com.backend.clinicaOdontologica.repository.impl;


import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.entity.Paciente;
import com.backend.clinicaOdontologica.entity.Turno;
import com.backend.clinicaOdontologica.repository.IDao;
import com.backend.clinicaOdontologica.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {
    private PacienteDaoH2 pacienteDaoH2;
    private OdontologoDaoH2 odontologoDaoH2;
    private final Logger LOGGER = Logger.getLogger(TurnoDaoH2.class);



    @Override
    public Turno registrar(Turno turno) {

        Connection connection = null;
        Turno turnoregistrado = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            /*

            ID LONG AUTO_INCREMENT PRIMARY KEY,
            PACIENTE_ID LONG NOT NULL,
            ODONTOLOGO_ID LONG NOT NULL,
            FECHA_HORA DATETIME NOT NULL,
    */

            pacienteDaoH2 = new PacienteDaoH2();
            Paciente pacienteRegistrado = pacienteDaoH2.registrar(turno.getPaciente());

            odontologoDaoH2 = new OdontologoDaoH2();
            Odontologo odontologoRegistrado = odontologoDaoH2.registrar(turno.getOdontologo());

            PreparedStatement preparedStatement = connection.prepareStatement(" INSERT INTO TURNOS (PACIENTE_ID, ODONTOLOGO_ID, FECHA_HORA) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, pacienteRegistrado.getId());
            preparedStatement.setLong(2, odontologoRegistrado.getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(turno.getFechaHora()));
            preparedStatement.execute();

            turnoregistrado = new Turno(turno.getId(), pacienteRegistrado, odontologoRegistrado, turno.getFechaHora());

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                turnoregistrado.setId(resultSet.getLong("id"));
            }
            connection.commit();

            LOGGER.info("Se ha registrado el turno correctamente." + turnoregistrado);
        } catch (Exception e) {
            LOGGER.error("Error al intentar registrar el turno: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error("Error al hacer rollback: " + exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();

            } catch (SQLException ex) {
                LOGGER.error("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return turnoregistrado;
    }

    @Override
    public Turno buscarPorId(Long id) {


        return null;
    }

    @Override
    public List<Turno> listarTodos() {
        Connection connection = null;
        List<Turno> turnos = new ArrayList<>();
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TURNOS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Turno turno = crearObjetoTurno(resultSet);
                turnos.add(turno);
            }

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        LOGGER.info("Listado de todos los Turnos registrados: " + turnos);

        return turnos;

    }


    private Turno crearObjetoTurno(ResultSet resultSet) throws SQLException {

        Paciente paciente = new PacienteDaoH2().buscarPorId(resultSet.getLong("paciente_id"));
        Odontologo odontologo = new OdontologoDaoH2().buscarPorId(resultSet.getLong("odontologo_id"));

        return new Turno(resultSet.getLong("id"), paciente, odontologo, resultSet.getTimestamp("Fecha_Hora").toLocalDateTime());
    }

}
